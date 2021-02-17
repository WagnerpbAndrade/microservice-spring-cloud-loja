package br.com.wagnerandrade.microservice.alura.loja.core.client;

import br.com.wagnerandrade.microservice.alura.loja.core.transport.InfoFornecedorDTO;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.InfoPedidoDTO;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.ItemDaCompraDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("fornecedor")
public interface FornecedorClient {

    @RequestMapping("/api/v1/info/{estado}")
    InfoFornecedorDTO getInfoPorEstado(@PathVariable String estado);

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/pedidos")
    InfoPedidoDTO realizaPedido(List<ItemDaCompraDTO> itens);
}
