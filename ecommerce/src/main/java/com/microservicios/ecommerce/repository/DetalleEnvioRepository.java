package com.microservicios.ecommerce.repository;

import com.microservicios.ecommerce.entities.DetalleEnvio;
import com.microservicios.ecommerce.entities.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DetalleEnvioRepository extends JpaRepository<DetalleEnvio, UUID> {

    //Buscar los detalles del envío por pedido Id
    Optional<DetalleEnvio> findByPedidoId(UUID pedidoId);

    //Buscar los detalles de envío para una transportadora
    List<DetalleEnvio> findByTransportadoraContainingIgnoreCase(String transportadora);

    //Buscar los detalles de envío por estado
    List<DetalleEnvio> findByPedido_Status(EstadoPedido status);
}
