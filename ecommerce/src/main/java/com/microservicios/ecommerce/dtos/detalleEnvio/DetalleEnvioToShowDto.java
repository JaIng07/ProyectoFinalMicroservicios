package com.microservicios.ecommerce.dtos.detalleEnvio;

import com.microservicios.ecommerce.dtos.pedido.PedidoToShowDto;

import java.util.UUID;

public record DetalleEnvioToShowDto (
        UUID id,
        String direccion,
        String transportadora,
        Integer numeroGuia,
        PedidoToShowDto pedido
) { }
