package com.microservicios.ecommerce.dtos.pedido;

import com.microservicios.ecommerce.dtos.cliente.ClienteDto;
import com.microservicios.ecommerce.dtos.detalleEnvio.DetalleEnvioDto;
import com.microservicios.ecommerce.dtos.itemPedido.ItemPedidoDto;
import com.microservicios.ecommerce.dtos.pago.PagoDto;
import com.microservicios.ecommerce.entities.EstadoPedido;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public record PedidoDto(
        UUID id,
        LocalDateTime fechaPedido,
        EstadoPedido status,
        PagoDto pago,
        DetalleEnvioDto detalleEnvio,
        ClienteDto cliente,
        List<ItemPedidoDto> itemsPedido

) {
    public List<ItemPedidoDto> itemsPedidos(){
        return Collections.unmodifiableList(itemsPedido);
    }
}
