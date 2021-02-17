package br.com.wagnerandrade.microservice.alura.loja.core.services;

import br.com.wagnerandrade.microservice.alura.loja.core.client.FornecedorClient;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.CompraDTO;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.InfoFornecedorDTO;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.InfoPedidoDTO;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.requests.CompraPostRequestDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompraService {

    private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

    private final FornecedorClient fornecedorClient;

    public CompraDTO realizaCompra(CompraPostRequestDTO compra) {
        String estado = compra.getEndereco().getEstado();

        LOG.info("Buscando informações do fornecedor de {}" + estado);
        InfoFornecedorDTO info = this.fornecedorClient.getInfoPorEstado(estado);

        LOG.info("Realizando um pedido");
        InfoPedidoDTO pedido = this.fornecedorClient.realizaPedido(compra.getItens());

        System.out.println(info.getEndereco());

        CompraDTO compraSalva = CompraDTO.builder()
                .pedidoId(pedido.getId())
                .tempoDePreparo(pedido.getTempoDePreparo())
                .enderecoDestino(compra.getEndereco().toString())
                .build();

        return compraSalva;
    }
}
