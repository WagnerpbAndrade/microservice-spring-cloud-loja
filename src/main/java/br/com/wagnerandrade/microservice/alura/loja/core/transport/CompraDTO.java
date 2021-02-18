package br.com.wagnerandrade.microservice.alura.loja.core.transport;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompraDTO {
    private Long pedidoId;

    private Integer tempoDePreparo;

    private String enderecoDestino;
}
