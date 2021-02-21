package br.com.wagnerandrade.microservice.alura.loja.core.transport;

import br.com.wagnerandrade.microservice.alura.loja.core.enums.CompraStateEnum;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompraDTO {
    private Long pedidoId;

    private Integer tempoDePreparo;

    private String enderecoDestino;

    private Long voucher;

    private LocalDate dataParaEntrega;

    @Enumerated(EnumType.STRING)
    private CompraStateEnum state;
}
