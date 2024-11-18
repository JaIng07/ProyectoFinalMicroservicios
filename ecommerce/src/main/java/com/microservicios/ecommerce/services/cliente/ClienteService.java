package com.microservicios.ecommerce.services.cliente;

import com.microservicios.ecommerce.dtos.cliente.ClienteToSaveDto;
import com.microservicios.ecommerce.dtos.cliente.ClienteToShowDto;

import java.util.List;
import java.util.UUID;

public interface ClienteService {

    ClienteToShowDto SaveCliente(ClienteToSaveDto cliente);

    ClienteToShowDto updateClienteById(UUID id, ClienteToSaveDto cliente);

    ClienteToShowDto findClienteById(UUID id);

    List<ClienteToShowDto> findAllCliente();

    void deleteClienteById(UUID id);

    ClienteToShowDto findClienteByEmail(String email);

    List<ClienteToShowDto> findClienteByDireccionContainingIgnoreCase(String direccion);

    List<ClienteToShowDto> findClienteByNombreStartingWithIgnoreCase(String nombre);
}
