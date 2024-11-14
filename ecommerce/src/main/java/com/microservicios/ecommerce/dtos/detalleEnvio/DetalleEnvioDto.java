package com.microservicios.ecommerce.dtos.detalleEnvio;

import com.microservicios.ecommerce.dtos.pedido.PedidoDto;

import java.util.UUID;

public record DetalleEnvioDto(
        UUID id,
        String direccion,
        String transportadora,
        Integer numeroGuia,
        PedidoDto pedido
) {
}
