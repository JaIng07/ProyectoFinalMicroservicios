package com.microservicios.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private Integer totalPago;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaPago;

    @Column
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private Pedido pedido;
}
