package com.microservicios.ecommerce.repository;

import com.microservicios.ecommerce.entities.MetodoPago;
import com.microservicios.ecommerce.entities.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PagoRepository  extends JpaRepository<Pago, UUID> {

    //Recuperar pagos dentro de un rango de fecha
    List<Pago> findByFechaPagoBetween(LocalDateTime startDate, LocalDateTime endDate);

    //Recuperar pagos por un identificador de una orden y método de pago
    Optional<Pago> findByPedidoIdAndMetodoPago(UUID PedidoId, MetodoPago metodoPago);
}
