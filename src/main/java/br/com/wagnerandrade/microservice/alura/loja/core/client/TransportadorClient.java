package br.com.wagnerandrade.microservice.alura.loja.core.client;

import br.com.wagnerandrade.microservice.alura.loja.core.transport.InfoEntregaDTO;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.VoucherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("transportador")
public interface TransportadorClient {

    @RequestMapping(path = "/api/v1/entregas", method = RequestMethod.POST)
    VoucherDTO reservaEntrega(InfoEntregaDTO pedidoDTO);
}
