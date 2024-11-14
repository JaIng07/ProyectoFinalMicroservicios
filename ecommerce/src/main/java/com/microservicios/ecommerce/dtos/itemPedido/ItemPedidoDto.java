package com.microservicios.ecommerce.dtos.itemPedido;

import com.microservicios.ecommerce.dtos.pedido.PedidoDto;
import com.microservicios.ecommerce.dtos.producto.ProductoDto;

import java.util.UUID;

public record ItemPedidoDto(
        UUID id,
        Integer cantidad,
        Float precioUnitario,
        PedidoDto pedido,
        ProductoDto producto
) { }
