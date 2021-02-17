package br.com.wagnerandrade.microservice.alura.loja.core.services;

import br.com.wagnerandrade.microservice.alura.loja.core.client.FornecedorClient;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.CompraDTO;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.InfoFornecedorDTO;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.InfoPedidoDTO;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.requests.CompraPostRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final FornecedorClient fornecedorClient;

    public CompraDTO realizaCompra(CompraPostRequestDTO compra) {
        InfoFornecedorDTO info = this.fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());

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
