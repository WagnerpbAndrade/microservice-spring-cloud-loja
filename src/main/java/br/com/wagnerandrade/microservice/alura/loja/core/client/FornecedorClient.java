package br.com.wagnerandrade.microservice.alura.loja.core.client;

import br.com.wagnerandrade.microservice.alura.loja.core.transport.InfoFornecedorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("fornecedor")
public interface FornecedorClient {

    @RequestMapping("/api/v1/info/{estado}")
    InfoFornecedorDTO getInfoPorEstado(@PathVariable String estado);
}
