package com.microservicios.ecommerce.controllers;

import com.microservicios.ecommerce.config.CustomMetricsBinder;
import com.microservicios.ecommerce.dtos.pedido.PedidoToSaveDto;
import com.microservicios.ecommerce.dtos.pedido.PedidoToShowDto;
import com.microservicios.ecommerce.entities.EstadoPedido;
import com.microservicios.ecommerce.exceptions.NotFoundException;
import com.microservicios.ecommerce.services.pedido.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class PedidoController {

    private final PedidoService pedidoServices;
    private final CustomMetricsBinder customMetricsBinder;

    @Autowired
    public  PedidoController(PedidoService pedidoServices, CustomMetricsBinder customMetricsBinder){
        this.pedidoServices = pedidoServices;
        this.customMetricsBinder = customMetricsBinder;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoToShowDto> getPedidoById(@PathVariable UUID id){
        try{
            PedidoToShowDto res = pedidoServices.findPedidoById(id);
            return ResponseEntity.ok().body(res);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PedidoToShowDto>> getAllPedido(){
        try{
            customMetricsBinder.incrementGetCounter();
            List<PedidoToShowDto> res = pedidoServices.findAllPedidos();
            return ResponseEntity.ok().body(res);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<PedidoToShowDto>> getPedidoByClienteIdAndStatus(@PathVariable UUID customerId, @RequestParam("status") String status){
        try{
            EstadoPedido statusPedido = EstadoPedido.fromString(status.toUpperCase());
            List<PedidoToShowDto> res = pedidoServices.findPedidosByClienteIdAndStatus(customerId, statusPedido);
            return ResponseEntity.ok().body(res);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<PedidoToShowDto>> getPedidoBetweenDates(@RequestParam("startDate") LocalDateTime startDate, @RequestParam("endDate") LocalDateTime endDate){
        try{
            List<PedidoToShowDto> res = pedidoServices.findPedidosByFechaPedidoBetween(startDate, endDate);
            return ResponseEntity.ok().body(res);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PedidoToShowDto> postPedido(@RequestBody PedidoToSaveDto pedido){
        try{
            PedidoToShowDto res = pedidoServices.savePedido(pedido);
            return ResponseEntity.ok().body(res);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoToShowDto> putPedido(@PathVariable UUID id, @RequestBody PedidoToSaveDto pedido){
        try{
            PedidoToShowDto res = pedidoServices.updatePedidoById(id, pedido);
            return ResponseEntity.ok().body(res);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> putPedido(@PathVariable UUID id){
        try{
            pedidoServices.deletePedidoById(id);
            return ResponseEntity.ok().body("Pedido Eliminado");
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
