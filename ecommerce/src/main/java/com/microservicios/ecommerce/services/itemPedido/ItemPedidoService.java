package com.microservicios.ecommerce.services.itemPedido;

import com.microservicios.ecommerce.dtos.itemPedido.ItemPedidoToSaveDto;
import com.microservicios.ecommerce.dtos.itemPedido.ItemPedidoToShowDto;

import java.util.List;
import java.util.UUID;

public interface ItemPedidoService {

    ItemPedidoToShowDto getItemPedidoById(UUID id);

    List<ItemPedidoToShowDto> getAllItemPedido();

    ItemPedidoToShowDto saveItemPedido(ItemPedidoToSaveDto itemPedidoToSaveDto);

    ItemPedidoToShowDto updateItemPedido(UUID itemPedidoId, ItemPedidoToSaveDto itemPedidoToSaveDto);

    List<ItemPedidoToShowDto> findItemPedidoToShowDtoByPedidoId(UUID pedidoId);

    List<ItemPedidoToShowDto> findItemPedidoToShowDtoByProductoId(UUID productoId);

    void deleteItemPedidoById(UUID id);
}
