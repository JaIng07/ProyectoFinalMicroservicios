package com.microservicios.ecommerce.controllers;

import com.microservicios.ecommerce.config.CustomMetricsBinder;
import com.microservicios.ecommerce.dtos.producto.ProductoToSaveDto;
import com.microservicios.ecommerce.dtos.producto.ProductoToShowDto;
import com.microservicios.ecommerce.exceptions.NotFoundException;
import com.microservicios.ecommerce.services.producto.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/products")
public class ProductoController {

    private final ProductoService productoServices;
    private final CustomMetricsBinder customMetricsBinder;

    @Autowired
    public ProductoController(ProductoService productoServices, CustomMetricsBinder customMetricsBinder) {
        this.productoServices = productoServices;
        this.customMetricsBinder=customMetricsBinder;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoToShowDto> getProductoById(@PathVariable UUID id) {
        try{
            ProductoToShowDto res = productoServices.findProductoById(id);
            return ResponseEntity.ok().body(res);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductoToShowDto>> getAllProductos() {
        try{
            customMetricsBinder.incrementGetCounter();
            List<ProductoToShowDto> res = productoServices.findAllProductos();
            return ResponseEntity.ok().body(res);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductoToShowDto>> getProductoByNombre(@RequestParam String searchTerm) {
        try{
            List<ProductoToShowDto> res = productoServices.findProductoByNombre(searchTerm);
            return ResponseEntity.ok().body(res);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/instock")
    public ResponseEntity<List<ProductoToShowDto>> getProductosInStock() {
        try{
            List<ProductoToShowDto> res = productoServices.findProductoInStock();
            return ResponseEntity.ok().body(res);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductoToShowDto> saveProducto(@RequestBody ProductoToSaveDto producto) {
        try {
            ProductoToShowDto res = productoServices.saveProducto(producto);
            return ResponseEntity.ok().body(res);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoToShowDto> updateProducto(@PathVariable UUID id, @RequestBody ProductoToSaveDto producto) {
        try{
            ProductoToShowDto updatedProducto = productoServices.updateProductoById(id, producto);
            return ResponseEntity.ok(updatedProducto);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable UUID id) {
        try{
            productoServices.deleteProductoById(id);
            return ResponseEntity.ok().body("Producto Eliminado");
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
