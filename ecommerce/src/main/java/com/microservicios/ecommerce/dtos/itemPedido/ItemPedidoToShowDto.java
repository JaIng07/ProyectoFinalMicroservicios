package com.microservicios.ecommerce.dtos.itemPedido;

import com.microservicios.ecommerce.dtos.pedido.PedidoToShowDto;
import com.microservicios.ecommerce.dtos.producto.ProductoToShowDto;

import java.util.UUID;

public record ItemPedidoToShowDto(
        UUID id,
        Integer cantidad,
        Float precioUnitario,
        PedidoToShowDto pedido,
        ProductoToShowDto producto
) { }
