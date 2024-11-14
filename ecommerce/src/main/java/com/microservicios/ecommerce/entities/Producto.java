package com.microservicios.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String nombre;

    @Column
    private Float price;

    @Column
    private Integer stock;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<ItemPedido> itemsPedidos;
}
