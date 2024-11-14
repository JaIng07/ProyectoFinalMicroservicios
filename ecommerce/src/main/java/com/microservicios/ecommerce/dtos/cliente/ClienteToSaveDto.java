package com.microservicios.ecommerce.dtos.cliente;

public record ClienteToSaveDto(
        String nombre,
        String email,
        String direccion
) { }
