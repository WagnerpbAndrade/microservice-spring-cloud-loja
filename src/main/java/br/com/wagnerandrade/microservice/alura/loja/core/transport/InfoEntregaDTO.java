package br.com.wagnerandrade.microservice.alura.loja.core.transport;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class InfoEntregaDTO {

    private Long pedidoId;

    private LocalDate dataParaEntrega;

    private String enderecoOrigem;

    private String enderecoDestino;
}
