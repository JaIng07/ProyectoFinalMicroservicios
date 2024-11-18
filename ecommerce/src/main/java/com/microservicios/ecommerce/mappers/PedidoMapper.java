package com.microservicios.ecommerce.mappers;

import com.microservicios.ecommerce.dtos.pedido.PedidoDto;
import com.microservicios.ecommerce.dtos.pedido.PedidoToSaveDto;
import com.microservicios.ecommerce.dtos.pedido.PedidoToShowDto;
import com.microservicios.ecommerce.entities.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    Pedido pedidoDtoToPedidoEntity(PedidoDto pedidoDto);

    @Mapping(target = "cliente", ignore = true)
    PedidoDto pedidoEntityToPedidoDto(Pedido pedido);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pago", ignore = true)
    @Mapping(target = "detalleEnvio",  ignore = true)
    @Mapping(target = "itemsPedido", expression = "java(new ArrayList<ItemPedido>())")
    @Mapping(target = "cliente.pedidos", expression = "java(new ArrayList<Pedido>())" )
    Pedido pedidoToSaveDtoToPedido(PedidoToSaveDto pedidoToSaveDto);

    PedidoToSaveDto pedidoEntityToPedidoToSaveDto(Pedido pedido);

    @Mapping(target = "pago", ignore = true)
    @Mapping(target = "detalleEnvio",  ignore = true)
    @Mapping(target = "itemsPedido",  ignore = true)
    @Mapping(target = "cliente.pedidos",  ignore = true)
    Pedido pedidoToShowDtoToPedidoEntity(PedidoToShowDto pedidoToShowDto);

    PedidoToShowDto pedidoEntityToPedidoToShow(Pedido pedido);
}
