package com.microservicios.ecommerce.dtos.pago;

import com.microservicios.ecommerce.dtos.pedido.PedidoDto;
import com.microservicios.ecommerce.entities.MetodoPago;

import java.time.LocalDateTime;
import java.util.UUID;

public record PagoDto(
        UUID id,
        Integer totalPago,
        LocalDateTime fechaPago,
        MetodoPago metodoPago,
        PedidoDto pedido
) { }
