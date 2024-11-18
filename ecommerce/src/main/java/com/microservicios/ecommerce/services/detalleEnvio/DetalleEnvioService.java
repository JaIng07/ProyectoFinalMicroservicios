package com.microservicios.ecommerce.services.detalleEnvio;

import com.microservicios.ecommerce.dtos.detalleEnvio.DetalleEnvioToSaveDto;
import com.microservicios.ecommerce.dtos.detalleEnvio.DetalleEnvioToShowDto;

import java.util.List;
import java.util.UUID;

public interface DetalleEnvioService {

    DetalleEnvioToShowDto getDetalleEnvioById(UUID id);

    List<DetalleEnvioToShowDto> getAllDetalleEnvio();

    DetalleEnvioToShowDto getDetalleEnvioByPedidoId(UUID pedido_id);

    List<DetalleEnvioToShowDto> getDetalleEnvioByTransportadora(String transportadora);

    DetalleEnvioToShowDto saveDetalleEnvio(DetalleEnvioToSaveDto detalleEnvioToSaveDto);

    DetalleEnvioToShowDto updateDetalleEnvioById(UUID id, DetalleEnvioToSaveDto detalleEnvioToSaveDto);

    void deleteDetalleEnvioById(UUID id);
}
