package com.microservicios.ecommerce.dtos.pedido;

import com.microservicios.ecommerce.dtos.cliente.ClienteToShowDto;
import com.microservicios.ecommerce.entities.EstadoPedido;

import java.time.LocalDateTime;

public record PedidoToSaveDto(
        LocalDateTime fechaPedido,
        EstadoPedido status,
        ClienteToShowDto cliente
) { }
