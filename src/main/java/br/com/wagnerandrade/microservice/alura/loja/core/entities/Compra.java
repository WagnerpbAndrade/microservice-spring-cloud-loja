package br.com.wagnerandrade.microservice.alura.loja.core.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

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
}