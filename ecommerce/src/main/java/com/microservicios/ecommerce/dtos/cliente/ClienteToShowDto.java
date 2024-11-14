package com.microservicios.ecommerce.dtos.cliente;

import java.util.UUID;

public record ClienteToShowDto(
        UUID id,
        String nombre,
        String email,
        String direccion
) { }
