package com.microservicios.ecommerce.controllers;

import com.microservicios.ecommerce.config.CustomMetricsBinder;
import com.microservicios.ecommerce.dtos.detalleEnvio.DetalleEnvioToSaveDto;
import com.microservicios.ecommerce.dtos.detalleEnvio.DetalleEnvioToShowDto;
import com.microservicios.ecommerce.exceptions.NotFoundException;
import com.microservicios.ecommerce.services.detalleEnvio.DetalleEnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/shipping")
public class DetalleEnvioController {

    private final DetalleEnvioService detalleEnvioServices;
    private final CustomMetricsBinder customMetricsBinder;

    @Autowired
    public DetalleEnvioController(DetalleEnvioService detalleEnvioServices, CustomMetricsBinder customMetricsBinder) {
        this.detalleEnvioServices = detalleEnvioServices;
        this.customMetricsBinder = customMetricsBinder;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleEnvioToShowDto> getDetalleEnvioById(@PathVariable UUID id) {
        try {
            DetalleEnvioToShowDto detalleEnvio = detalleEnvioServices.getDetalleEnvioById(id);
            return ResponseEntity.ok().body(detalleEnvio);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<DetalleEnvioToShowDto>> getAllDetalleEnvio() {
        try {
            customMetricsBinder.incrementGetCounter();
            List<DetalleEnvioToShowDto> detallesEnvio = detalleEnvioServices.getAllDetalleEnvio();
            return ResponseEntity.ok().body(detallesEnvio);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<DetalleEnvioToShowDto> getDetalleEnvioByPedidoId(@PathVariable UUID orderId) {
        try {
            DetalleEnvioToShowDto detalleEnvio = detalleEnvioServices.getDetalleEnvioByPedidoId(orderId);
            return ResponseEntity.ok().body(detalleEnvio);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/carrier")
    public ResponseEntity<List<DetalleEnvioToShowDto>> getDetalleEnvioByTransportadora(@RequestParam("name") String transporter) {
        try {
            List<DetalleEnvioToShowDto> detallesEnvio = detalleEnvioServices.getDetalleEnvioByTransportadora(transporter);
            return ResponseEntity.ok().body(detallesEnvio);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DetalleEnvioToShowDto> postDetalleEnvio(@RequestBody DetalleEnvioToSaveDto detalleEnvioToSaveDto) {
        DetalleEnvioToShowDto detalleEnvio = detalleEnvioServices.saveDetalleEnvio(detalleEnvioToSaveDto);
        return ResponseEntity.ok().body(detalleEnvio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleEnvioToShowDto> putDetalleEnvio(@PathVariable UUID id, @RequestBody DetalleEnvioToSaveDto detalleEnvioToSaveDto) {
        try {
            DetalleEnvioToShowDto detalleEnvio = detalleEnvioServices.updateDetalleEnvioById(id, detalleEnvioToSaveDto);
            return ResponseEntity.ok().body(detalleEnvio);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDetalleEnvio(@PathVariable UUID id) {
        try {
            detalleEnvioServices.deleteDetalleEnvioById(id);
            return ResponseEntity.ok().body("Detalle de envío eliminado");
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
