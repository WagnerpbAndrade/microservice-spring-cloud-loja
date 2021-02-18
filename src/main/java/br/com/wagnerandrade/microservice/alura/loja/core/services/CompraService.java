package br.com.wagnerandrade.microservice.alura.loja.core.services;

import br.com.wagnerandrade.microservice.alura.loja.core.client.FornecedorClient;
import br.com.wagnerandrade.microservice.alura.loja.core.entities.Compra;
import br.com.wagnerandrade.microservice.alura.loja.core.mappers.CompraMapper;
import br.com.wagnerandrade.microservice.alura.loja.core.repositories.CompraRepository;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.CompraDTO;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.InfoFornecedorDTO;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.InfoPedidoDTO;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.requests.CompraPostRequestDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompraService {

    private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

    private final FornecedorClient fornecedorClient;
    private final CompraRepository compraRepository;
    private final CompraMapper compraMapper;

    @Retryable(
            value = RuntimeException.class,
            backoff = @Backoff(delay = 50, maxDelay = 100)
    )
    public CompraDTO getById(Long id) {
        return this.compraMapper.toCompraDTO(this.compraRepository.findById(id).orElse(Compra.builder().build()));
    }

    @Retryable(
            value = RuntimeException.class,
            backoff = @Backoff(delay = 50, maxDelay = 100)
    )
    public CompraDTO realizaCompra(CompraPostRequestDTO compra) {
        String estado = compra.getEndereco().getEstado();

        LOG.info("Buscando informações do fornecedor de {}" + estado);
        InfoFornecedorDTO info = this.fornecedorClient.getInfoPorEstado(estado);

        LOG.info("Realizando um pedido");
        InfoPedidoDTO pedido = this.fornecedorClient.realizaPedido(compra.getItens());

        System.out.println(info.getEndereco());

        Compra compraSalva = Compra.builder()
                .pedidoId(pedido.getId())
                .tempoDePreparo(pedido.getTempoDePreparo())
                .enderecoDestino(compra.getEndereco().toString())
                .build();

        return this.compraMapper.toCompraDTO(this.compraRepository.save(compraSalva));
    }

    @Recover
    public CompraDTO realizaCompraFallback(RuntimeException exception, CompraDTO compra) {
        LOG.info("Entrou no Fallback");
        CompraDTO compraFallback = CompraDTO.builder().enderecoDestino(compra.getEnderecoDestino()).build();
        return compraFallback;
    }
}
