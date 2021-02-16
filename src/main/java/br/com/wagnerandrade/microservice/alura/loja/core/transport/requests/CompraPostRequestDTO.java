package br.com.wagnerandrade.microservice.alura.loja.core.transport.requests;

import br.com.wagnerandrade.microservice.alura.loja.core.transport.EnderecoDTO;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.ItemDaCompraDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CompraPostRequestDTO {

    private List<ItemDaCompraDTO> itens;
    private EnderecoDTO endereco;
}
