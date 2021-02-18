package br.com.wagnerandrade.microservice.alura.loja.core.mappers;

import br.com.wagnerandrade.microservice.alura.loja.core.entities.Compra;
import br.com.wagnerandrade.microservice.alura.loja.core.transport.CompraDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompraMapper {
    CompraMapper instance = Mappers.getMapper(CompraMapper.class);

    CompraDTO toCompraDTO(Compra compra);
}
