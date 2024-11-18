package com.microservicios.ecommerce.controllers;

import com.microservicios.ecommerce.config.CustomMetricsBinder;
import com.microservicios.ecommerce.dtos.itemPedido.ItemPedidoToSaveDto;
import com.microservicios.ecommerce.dtos.itemPedido.ItemPedidoToShowDto;
import com.microservicios.ecommerce.exceptions.NotFoundException;
import com.microservicios.ecommerce.services.itemPedido.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/order-items")
public class ItemPedidoController {

    ItemPedidoService itemPedidoServices;
    private final CustomMetricsBinder customMetricsBinder;

    @Autowired
    public ItemPedidoController(ItemPedidoService itemPedidoServices, CustomMetricsBinder customMetricsBinder){

        this.itemPedidoServices = itemPedidoServices;
        this.customMetricsBinder = customMetricsBinder;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoToShowDto> getItemPedidoById(@PathVariable UUID id){
        try {
            ItemPedidoToShowDto res = itemPedidoServices.getItemPedidoById(id);
            return ResponseEntity.ok().body(res);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ItemPedidoToShowDto>> getAllItemPedido(){
        try {
            customMetricsBinder.incrementGetCounter();
            List<ItemPedidoToShowDto> res = itemPedidoServices.getAllItemPedido();
            return ResponseEntity.ok().body(res);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<ItemPedidoToShowDto>> getItemPedidoByOrderId(@PathVariable UUID orderId){
        try {
            List<ItemPedidoToShowDto> res = itemPedidoServices.findItemPedidoToShowDtoByPedidoId(orderId);
            return ResponseEntity.ok().body(res);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/product/{productoId}")
    public ResponseEntity<List<ItemPedidoToShowDto>> getItemPedidoByProductoId(@PathVariable UUID productoId){
        try {
            List<ItemPedidoToShowDto> res = itemPedidoServices.findItemPedidoToShowDtoByProductoId(productoId);
            return ResponseEntity.ok().body(res);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ItemPedidoToShowDto> postItemPedido(@RequestBody ItemPedidoToSaveDto itemPedido){
        try{
            ItemPedidoToShowDto res = itemPedidoServices.saveItemPedido(itemPedido);
            return ResponseEntity.ok().body(res);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoToShowDto> putItemPedido(@PathVariable UUID id, @RequestBody ItemPedidoToSaveDto itemPedido){
        try{
            ItemPedidoToShowDto res = itemPedidoServices.updateItemPedido(id, itemPedido);
            return ResponseEntity.ok().body(res);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItemPedido(@PathVariable UUID id){
        try{
            itemPedidoServices.deleteItemPedidoById(id);
            return ResponseEntity.ok().body("ItemPedido con ID " + id + " eliminado");
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
