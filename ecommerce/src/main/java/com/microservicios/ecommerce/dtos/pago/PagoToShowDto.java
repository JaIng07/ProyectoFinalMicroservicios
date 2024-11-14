package com.microservicios.ecommerce.dtos.pago;

import com.microservicios.ecommerce.dtos.pedido.PedidoToShowDto;
import com.microservicios.ecommerce.entities.MetodoPago;
import java.time.LocalDateTime;
import java.util.UUID;

public record PagoToShowDto (
        UUID id,
        Integer totalPago,
        LocalDateTime fechaPago,
        MetodoPago metodoPago,
        PedidoToShowDto pedido
){ }
