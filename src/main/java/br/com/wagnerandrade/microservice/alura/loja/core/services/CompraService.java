package br.com.wagnerandrade.microservice.alura.loja.core.services;

import br.com.wagnerandrade.microservice.alura.loja.core.client.FornecedorClient;
import br.com.wagnerandrade.microservice.alura.loja.core.client.TransportadorClient;
import br.com.wagnerandrade.microservice.alura.loja.core.entities.Compra;
import br.com.wagnerandrade.microservice.alura.loja.core.enums.CompraStateEnum;
import br.com.wagnerandrade.microservice.alura.loja.core.mappers.CompraMapper;
import br.com.wagnerandrade.microservice.alura.loja.core.repositories.CompraRepository;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.*;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.requests.CompraPostRequestDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CompraService {

    private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

    private final FornecedorClient fornecedorClient;

    private final CompraRepository compraRepository;

    private final TransportadorClient transportadorClient;

    private final CompraMapper compraMapper;

    @HystrixCommand(threadPoolKey = "getByIdThreadPool")
    public CompraDTO getById(Long id) {
        return this.compraMapper.toCompraDTO(this.compraRepository.findById(id).orElse(new Compra()));
    }

    @HystrixCommand(
            fallbackMethod = "realizaCompraFallback",
            threadPoolKey = "realizaCompraThreadPool"
    )
    public CompraDTO realizaCompra(CompraPostRequestDTO compra) {
        Compra compraSalva = new Compra();

        compraSalva.setState(CompraStateEnum.RECEBIDO);
        compraSalva.setEnderecoDestino(compra.getEndereco().toString());
        this.compraRepository.save(compraSalva);

        String estado = compra.getEndereco().getEstado();
        InfoFornecedorDTO info = this.fornecedorClient.getInfoPorEstado(estado);
        InfoPedidoDTO pedido = this.fornecedorClient.realizaPedido(compra.getItens());

        compraSalva.setState(CompraStateEnum.PEDIDO_REALIZADO);
        compraSalva.setPedidoId(pedido.getId());
        compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
        this.compraRepository.save(compraSalva);

        InfoEntregaDTO entregaDTO = InfoEntregaDTO.builder()
                .pedidoId(pedido.getId())
                .dataParaEntrega(LocalDate.now().plusDays(pedido.getTempoDePreparo()))
                .enderecoOrigem(info.getEndereco())
                .enderecoDestino(compra.getEndereco().toString())
                .build();
        VoucherDTO voucherDTO = this.transportadorClient.reservaEntrega(entregaDTO);

        compraSalva.setState(CompraStateEnum.RESERVA_ENTREGA_REALIZADA);
        compraSalva.setVoucher(voucherDTO.getNumero());
        compraSalva.setDataParaEntrega(voucherDTO.getPrevisaoParaEntrega());
        this.compraRepository.save(compraSalva);

        return this.compraMapper.toCompraDTO(this.compraRepository.save(compraSalva));
    }


    public CompraDTO realizaCompraFallback(CompraPostRequestDTO compra) {
        LOG.info("Entrou no Fallback");
        CompraDTO compraFallback = CompraDTO.builder().enderecoDestino(compra.getEndereco().getEstado()).build();
        return compraFallback;
    }
}
