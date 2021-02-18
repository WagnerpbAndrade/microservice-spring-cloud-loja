package br.com.wagnerandrade.microservice.alura.loja.core.entities;

import br.com.wagnerandrade.microservice.alura.loja.core.transport.VoucherDTO;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compra {

    @Id
    private Long pedidoId;

    private Integer tempoDePreparo;

    private String enderecoDestino;

    private Long voucher;

    private LocalDate dataParaEntrega;
}