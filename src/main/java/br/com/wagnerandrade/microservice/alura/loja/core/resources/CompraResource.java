package br.com.wagnerandrade.microservice.alura.loja.core.resources;

import br.com.wagnerandrade.microservice.alura.loja.core.services.CompraService;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.CompraDTO;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.requests.CompraPostRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/compras")
@RequiredArgsConstructor
public class CompraResource {

    private final CompraService compraService;

    @GetMapping("/{id}")
    public ResponseEntity<CompraDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.compraService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody CompraPostRequestDTO compra) {
        return ResponseEntity.ok().body(this.compraService.realizaCompra(compra));
    }

}
