package com.microservicios.ecommerce.dtos.producto;

public record ProductoToSaveDto(
        String nombre,
        Float price,
        Integer stock
) { }
