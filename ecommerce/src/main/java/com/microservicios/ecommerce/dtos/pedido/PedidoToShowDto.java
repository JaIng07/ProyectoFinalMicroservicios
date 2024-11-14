package com.microservicios.ecommerce.dtos.pedido;

import com.microservicios.ecommerce.dtos.cliente.ClienteToShowDto;
import com.microservicios.ecommerce.entities.EstadoPedido;

import java.time.LocalDateTime;
import java.util.UUID;

public record PedidoToShowDto(
        UUID id,
        LocalDateTime fechaPedido,
        EstadoPedido status,
        ClienteToShowDto cliente
) { }
