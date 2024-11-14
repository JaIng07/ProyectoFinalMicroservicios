package com.microservicios.ecommerce.dtos.itemPedido;

import com.microservicios.ecommerce.dtos.pedido.PedidoToShowDto;
import com.microservicios.ecommerce.dtos.producto.ProductoToShowDto;

public record ItemPedidoToSaveDto(
        Integer cantidad,
        Float precioUnitario,
        PedidoToShowDto pedido,
        ProductoToShowDto producto
) { }
