package br.com.wagnerandrade.microservice.alura.loja.core.entities;

import br.com.wagnerandrade.microservice.alura.loja.core.enums.CompraStateEnum;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.VoucherDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;

    private Integer tempoDePreparo;

    private String enderecoDestino;

    private Long voucher;

    @Enumerated(EnumType.STRING)
    private CompraStateEnum state;

    private LocalDate dataParaEntrega;
}