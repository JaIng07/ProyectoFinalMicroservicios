package com.microservicios.ecommerce.controllers;

import com.microservicios.ecommerce.config.CustomMetricsBinder;
import com.microservicios.ecommerce.dtos.cliente.ClienteToSaveDto;
import com.microservicios.ecommerce.dtos.cliente.ClienteToShowDto;
import com.microservicios.ecommerce.exceptions.NotFoundException;
import com.microservicios.ecommerce.services.cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/customers")
public class ClienteController {

    private final ClienteService clienteServices;
    private final CustomMetricsBinder customMetricsBinder;


    @Autowired
    public ClienteController(ClienteService clienteServices, CustomMetricsBinder customMetricsBinder){
        this.customMetricsBinder = customMetricsBinder;
        this.clienteServices = clienteServices;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteToShowDto> getClienteById(@PathVariable UUID id){
        try {
            ClienteToShowDto res = clienteServices.findClienteById(id);
            return ResponseEntity.ok().body(res);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // http://localhost:8080/actuator/metrics/customers.requests
    @GetMapping
    public ResponseEntity<List<ClienteToShowDto>> getAllCliente(){
        try {
            customMetricsBinder.incrementCustomersCounter(); // <-- increments the counter
            customMetricsBinder.incrementGetCounter();
            List<ClienteToShowDto> res = clienteServices.findAllCliente();
            return ResponseEntity.ok().body(res);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ClienteToShowDto> getClienteByEmail(@PathVariable String email){
        try {
            ClienteToShowDto res = clienteServices.findClienteByEmail(email);
            return ResponseEntity.ok().body(res);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/city")
    public ResponseEntity<List<ClienteToShowDto>> getClientesByCityName(@RequestParam("cityName") String address){
        try {
            List<ClienteToShowDto> res = clienteServices.findClienteByDireccionContainingIgnoreCase(address);
            return ResponseEntity.ok().body(res);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<ClienteToShowDto> postCliente(@RequestBody ClienteToSaveDto cliente){
        ClienteToShowDto res = clienteServices.SaveCliente(cliente);
        return ResponseEntity.ok().body(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteToShowDto> putCliente(@PathVariable UUID id, @RequestBody ClienteToSaveDto cliente){
        try{
            ClienteToShowDto res = clienteServices.updateClienteById(id, cliente);
            return ResponseEntity.ok().body(res);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable UUID id){
        try{
            clienteServices.deleteClienteById(id);
            return ResponseEntity.ok().body("Cliente eliminado");
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
