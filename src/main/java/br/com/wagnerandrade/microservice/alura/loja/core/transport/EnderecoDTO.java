package br.com.wagnerandrade.microservice.alura.loja.core.transport;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EnderecoDTO {

    private String rua;
    private String numero;
    private String estado;
}
