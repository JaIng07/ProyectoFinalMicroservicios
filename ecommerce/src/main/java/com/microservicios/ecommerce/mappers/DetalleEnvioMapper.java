package com.microservicios.ecommerce.mappers;

import com.microservicios.ecommerce.dtos.detalleEnvio.DetalleEnvioDto;
import com.microservicios.ecommerce.dtos.detalleEnvio.DetalleEnvioToSaveDto;
import com.microservicios.ecommerce.dtos.detalleEnvio.DetalleEnvioToShowDto;
import com.microservicios.ecommerce.entities.DetalleEnvio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetalleEnvioMapper {

    DetalleEnvio detalleEnvioDtoToDetalleEnvioEntity(DetalleEnvioDto detalleEnvioDto);

    DetalleEnvioDto detalleEnvioEntityToDetalleEnvioDto(DetalleEnvio detalleEnvio);

    @Mapping(target = "id", ignore = true)
    DetalleEnvio detalleEnvioToSaveDtoToDetalleEnvioEntity(DetalleEnvioToSaveDto detalleEnvioToSaveDto);

    DetalleEnvioToSaveDto detalleEnvioEntityToDetalleEnvioToSaveDto(DetalleEnvio detalleEnvio);

    @Mapping( target = "pedido", ignore = true)
    DetalleEnvio detalleEnvioToShowDtoToDetalleEnvioEntity(DetalleEnvioToShowDto detalleEnvioToShowDto);

    DetalleEnvioToShowDto detalleEnvioEntityToDetalleEnvioToShowDto(DetalleEnvio detalleEnvio);
}
