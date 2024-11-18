package com.microservicios.ecommerce.services.producto;

import com.microservicios.ecommerce.dtos.producto.ProductoToSaveDto;
import com.microservicios.ecommerce.dtos.producto.ProductoToShowDto;

import java.util.List;
import java.util.UUID;

public interface ProductoService {

    List<ProductoToShowDto> findAllProductos();

    ProductoToShowDto findProductoById(UUID id);

    List<ProductoToShowDto>  findProductoByNombre(String nombre);

    List<ProductoToShowDto> findProductoInStock();

    ProductoToShowDto saveProducto(ProductoToSaveDto producto);

    ProductoToShowDto updateProductoById(UUID id, ProductoToSaveDto producto);

    void deleteProductoById(UUID id);
}
