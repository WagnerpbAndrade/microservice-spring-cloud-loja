package br.com.wagnerandrade.microservice.alura.loja.core.resources;

import br.com.wagnerandrade.microservice.alura.loja.core.services.CompraService;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.requests.CompraPostRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/compras")
@RequiredArgsConstructor
public class CompraResource {

    private final CompraService compraService;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody CompraPostRequestDTO compra) {
        this.compraService.realizaCompra(compra);
        return ResponseEntity.ok().build();
    }

}
