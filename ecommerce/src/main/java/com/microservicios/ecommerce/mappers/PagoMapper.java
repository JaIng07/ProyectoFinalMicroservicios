package com.microservicios.ecommerce.mappers;

import com.microservicios.ecommerce.dtos.pago.PagoDto;
import com.microservicios.ecommerce.dtos.pago.PagoToSaveDto;
import com.microservicios.ecommerce.dtos.pago.PagoToShowDto;
import com.microservicios.ecommerce.entities.Pago;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PagoMapper {

    Pago pagoDtoToPagoEntity(PagoDto pagoDto);

    PagoDto pagoEntityToPagoDto(Pago pago);

    Pago pagoToSaveDtoToPagoEntity(PagoToSaveDto pagoToSaveDto);

    PagoToSaveDto pagoEntityToPagoToSaveDto(Pago pago);

    Pago pagoToShowDtoToPagoEntity(PagoToShowDto pagoToShowDto);

    PagoToShowDto pagoEntityToPagoToShowDto(Pago pago);
}
