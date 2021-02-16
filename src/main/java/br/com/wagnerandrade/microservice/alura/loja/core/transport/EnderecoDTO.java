package br.com.wagnerandrade.microservice.alura.loja.core.transport;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {

    private String logradouro;
    private String numero;
    private String estado;
}
