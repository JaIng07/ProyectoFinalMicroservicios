package com.microservicios.ecommerce.dtos.producto;

import java.util.UUID;

public record ProductoToShowDto(
        UUID id,
        String nombre,
        Float price,
        Integer stock
) { }
