package com.microservicios.ecommerce.dtos.producto;

import com.microservicios.ecommerce.dtos.itemPedido.ItemPedidoDto;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public record ProductoDto(
        UUID id,
        String nombre,
        Float price,
        Integer stock,
        List<ItemPedidoDto> itemsPedidos
) {

    public List<ItemPedidoDto> itemPedidos(){
        return Collections.unmodifiableList(itemsPedidos);
    }

}
