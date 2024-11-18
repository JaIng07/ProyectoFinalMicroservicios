package com.microservicios.ecommerce.services.pago;

import com.microservicios.ecommerce.dtos.pago.PagoToSaveDto;
import com.microservicios.ecommerce.dtos.pago.PagoToShowDto;
import com.microservicios.ecommerce.entities.MetodoPago;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PagoService {

    PagoToShowDto findPagoById(UUID id);

    List<PagoToShowDto> findAllPago();

    PagoToShowDto findPagoByPedidoIdAndMetodoPago(UUID PedidoId, MetodoPago metodoPago);

    List<PagoToShowDto> findPagoByFechaPagoBetween(LocalDateTime startDate, LocalDateTime endDate);

    PagoToShowDto savePago(PagoToSaveDto pagoToSaveDto);

    PagoToShowDto updatePagoById(UUID pagoId, PagoToSaveDto pagoToSaveDto);

    void deletePagoById(UUID pagoId);
}
