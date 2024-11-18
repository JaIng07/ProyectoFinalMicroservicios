package com.microservicios.ecommerce.mappers;

import com.microservicios.ecommerce.dtos.itemPedido.ItemPedidoDto;
import com.microservicios.ecommerce.dtos.itemPedido.ItemPedidoToSaveDto;
import com.microservicios.ecommerce.dtos.itemPedido.ItemPedidoToShowDto;
import com.microservicios.ecommerce.entities.ItemPedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {

    ItemPedido itemPedidoDtoToItemPedidoEntity(ItemPedidoDto itemPedidoDto);

    ItemPedidoDto itemPedidoEntityToItemPedidoDto(ItemPedido itemPedido);

    @Mapping(target = "id", ignore = true)
    ItemPedido itemPedidoToSaveDtoToItemPedidoEntity(ItemPedidoToSaveDto itemPedidoToSaveDto);

    ItemPedidoToSaveDto itemPedidoEntityToItemPedidoToSaveDto(ItemPedido itemPedido);

    ItemPedido itemPedidoToShowDtoToItemPedidoEntity(ItemPedidoToShowDto itemPedidoToShowDto);

    ItemPedidoToShowDto itemPedidoEntityToItemPedidoToShowDto(ItemPedido itemPedido);
}
