package com.microservicios.ecommerce.dtos.detalleEnvio;

import com.microservicios.ecommerce.dtos.pedido.PedidoToShowDto;

public record DetalleEnvioToSaveDto(
        String direccion,
        String transportadora,
        Integer numeroGuia,
        PedidoToShowDto pedido
) { }
