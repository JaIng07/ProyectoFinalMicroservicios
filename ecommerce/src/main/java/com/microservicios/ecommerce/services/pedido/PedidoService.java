package com.microservicios.ecommerce.services.pedido;

import com.microservicios.ecommerce.dtos.pedido.PedidoToSaveDto;
import com.microservicios.ecommerce.dtos.pedido.PedidoToShowDto;
import com.microservicios.ecommerce.entities.EstadoPedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PedidoService {

    PedidoToShowDto savePedido(PedidoToSaveDto pedido );

    PedidoToShowDto updatePedidoById(UUID id, PedidoToSaveDto pedido );

    PedidoToShowDto findPedidoById(UUID id);

    List<PedidoToShowDto> findAllPedidos();

    void deletePedidoById(UUID id);

    List<PedidoToShowDto> findPedidosByFechaPedidoBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<PedidoToShowDto> findPedidosByClienteIdAndStatus(UUID cliente_id, EstadoPedido status);

    List<PedidoToShowDto> findPedidosByClienteIdWithItemsFetch(UUID cliente_id);
}
