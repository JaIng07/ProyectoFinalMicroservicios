package com.microservicios.ecommerce.repository;

import com.microservicios.ecommerce.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    // Encontrar cliente(s) por email
    Optional<Cliente> findByEmail(String email);

    // Encontrar clientes por dirección
    List<Cliente> findByDireccionContainingIgnoreCase(String direccion);

    // Encontrar clientes por todos los clientes que comiencen por un nombre
    List<Cliente> findByNombreStartingWithIgnoreCase(String nombre);
}
