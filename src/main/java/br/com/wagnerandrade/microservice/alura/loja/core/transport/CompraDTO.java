package br.com.wagnerandrade.microservice.alura.loja.core.transport;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CompraDTO {
    private Long pedidoId;

    private Integer tempoDePreparo;

    private String enderecoDestino;
}
