package br.com.wagnerandrade.microservice.alura.loja.core.services;

import br.com.wagnerandrade.microservice.alura.loja.core.transport.InfoFornecedorDTO;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.requests.CompraPostRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final RestTemplate client;

    private final DiscoveryClient discoveryClient;

    public void realizaCompra(CompraPostRequestDTO compra) {
        ResponseEntity<InfoFornecedorDTO> exchange = client.exchange("http://fornecedor/api/v1/info/" + compra.getEndereco().getEstado(),
                HttpMethod.GET, null, InfoFornecedorDTO.class);

        this.discoveryClient.getInstances("fornecedor").stream()
                .forEach(fornecedor -> {
                    System.out.println("localhost: " + fornecedor.getPort());
                });

        System.out.println(exchange.getBody().getEndereco());
    }
}
