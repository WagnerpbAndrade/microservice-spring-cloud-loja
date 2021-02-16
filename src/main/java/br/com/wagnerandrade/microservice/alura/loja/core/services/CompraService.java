package br.com.wagnerandrade.microservice.alura.loja.core.services;

import br.com.wagnerandrade.microservice.alura.loja.core.client.FornecedorClient;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.InfoFornecedorDTO;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.requests.CompraPostRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final FornecedorClient fornecedorClient;

    public void realizaCompra(CompraPostRequestDTO compra) {
        InfoFornecedorDTO info = this.fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());

        System.out.println(info.getEndereco());
    }
}
