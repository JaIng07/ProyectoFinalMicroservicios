package com.microservicios.ecommerce.services.producto;

import com.microservicios.ecommerce.dtos.producto.ProductoToSaveDto;
import com.microservicios.ecommerce.dtos.producto.ProductoToShowDto;
import com.microservicios.ecommerce.entities.Producto;
import com.microservicios.ecommerce.exceptions.NotFoundException;
import com.microservicios.ecommerce.mappers.ProductoMapper;
import com.microservicios.ecommerce.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductoServiceImpl implements ProductoService {

    ProductoRepository productoRepository;
    ProductoMapper productoMapper;

    public ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapper productoMapper){
        this.productoMapper = productoMapper;
        this.productoRepository = productoRepository;
    }

    @Override
    public List<ProductoToShowDto> findAllProductos() {

        List<Producto> productos = productoRepository.findAll();

        if (productos.isEmpty())
            throw new NotFoundException("No hay productos registrado");

        List<ProductoToShowDto> productosADevolver = new ArrayList<>();

        productos.forEach( producto -> {
            ProductoToShowDto p = productoMapper.productoEntityToProductoToShowDto(producto);
            productosADevolver.add(p);
        });

        return productosADevolver;
    }

    @Override
    public ProductoToShowDto findProductoById(UUID id) {
        Optional<Producto> producto = productoRepository.findById(id);

        if (producto.isEmpty())
            throw new NotFoundException("Producto con ID " + id + " no encontrado");

        return productoMapper.productoEntityToProductoToShowDto(producto.get());
    }

    @Override
    public List<ProductoToShowDto> findProductoByNombre(String nombre) {
        List<Producto> productoMatch = productoRepository.findByNombreContainingIgnoreCase(nombre);

        if (productoMatch.isEmpty())
            throw new NotFoundException("No se encontró productos que contengan " + nombre);

        List<ProductoToShowDto> productosARegresar = new ArrayList<>();

        productoMatch.forEach(producto -> {
            ProductoToShowDto productoMappeado = productoMapper.productoEntityToProductoToShowDto(producto);
            productosARegresar.add(productoMappeado);
        });

        return productosARegresar;
    }

    @Override
    public List<ProductoToShowDto> findProductoInStock() {
        List<Producto> productosEnStock = productoRepository.findInStock();

        if (productosEnStock.isEmpty())
            throw new NotFoundException("No hay productos en stock");

        List<ProductoToShowDto> productosARegresar = new ArrayList<>();

        productosEnStock.forEach(producto -> {
            ProductoToShowDto productoMappeado = productoMapper.productoEntityToProductoToShowDto(producto);
            productosARegresar.add(productoMappeado);
        });

        return productosARegresar;
    }

    @Override
    public ProductoToShowDto saveProducto(ProductoToSaveDto producto) {
        Producto productoAGuardar = productoMapper.productoToSaveDtoToProductoEntity(producto);
        Producto productoGuardado = productoRepository.save(productoAGuardar);
        return productoMapper.productoEntityToProductoToShowDto(productoGuardado);
    }

    @Override
    public ProductoToShowDto updateProductoById(UUID id, ProductoToSaveDto producto) {
        Optional<Producto> productoConsultado = productoRepository.findById(id);

        if (productoConsultado.isEmpty())
            throw new NotFoundException("Producto con ID " + id + " no encontrado");

        Producto pr = productoConsultado.get();

        if (producto.nombre() != null) pr.setNombre(producto.nombre());
        if (producto.stock() != null) pr.setStock(producto.stock());
        if (producto.price() != null) pr.setPrice(producto.price());

        Producto productoActualizado = productoRepository.save(pr);

        return productoMapper.productoEntityToProductoToShowDto(productoActualizado);
    }

    @Override
    public void deleteProductoById(UUID id) {
        Optional<Producto> productoAEliminar = productoRepository.findById(id);

        if (productoAEliminar.isEmpty())
            throw new NotFoundException("Producto con ID " + id + " no existe");

        productoRepository.deleteById(id);
    }
}
