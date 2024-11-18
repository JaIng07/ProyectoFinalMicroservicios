package com.microservicios.ecommerce.repository;

import com.microservicios.ecommerce.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductoRepository extends JpaRepository<Producto, UUID> {

    //Buscar productos según un término de búsqueda
    List<Producto> findByNombreContainingIgnoreCase(String searchTerm);

    // Buscar los productos que están en stock.
    @Query("SELECT p FROM Producto p WHERE p.stock > 0")
    List<Producto> findInStock();

    // Buscar los productos que no superen un precio y un stock determinado
    List<Producto> findByPriceLessThanAndStockLessThan(Float precio, Integer stock);
}
