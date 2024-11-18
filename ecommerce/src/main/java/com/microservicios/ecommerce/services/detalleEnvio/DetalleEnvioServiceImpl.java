package com.microservicios.ecommerce.services.detalleEnvio;

import com.microservicios.ecommerce.dtos.detalleEnvio.DetalleEnvioToSaveDto;
import com.microservicios.ecommerce.dtos.detalleEnvio.DetalleEnvioToShowDto;
import com.microservicios.ecommerce.entities.DetalleEnvio;
import com.microservicios.ecommerce.entities.Pedido;
import com.microservicios.ecommerce.exceptions.NotFoundException;
import com.microservicios.ecommerce.mappers.DetalleEnvioMapper;
import com.microservicios.ecommerce.repository.DetalleEnvioRepository;
import com.microservicios.ecommerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DetalleEnvioServiceImpl implements DetalleEnvioService {

    DetalleEnvioMapper detalleEnvioMapper;
    DetalleEnvioRepository detalleEnvioRepository;

    PedidoRepository pedidoRepository;

    @Autowired
    public DetalleEnvioServiceImpl(DetalleEnvioMapper detalleEnvioMapper, DetalleEnvioRepository detalleEnvioRepository, PedidoRepository pedidoRepository){
        this.detalleEnvioMapper = detalleEnvioMapper;
        this.detalleEnvioRepository = detalleEnvioRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public DetalleEnvioToShowDto getDetalleEnvioById(UUID id) {

        Optional<DetalleEnvio> detalleEnvio = detalleEnvioRepository.findById(id);

        if(detalleEnvio.isEmpty())
            throw new NotFoundException("No se ha encontrado el detalle de envio con ese id " + id);

        return detalleEnvioMapper.detalleEnvioEntityToDetalleEnvioToShowDto(detalleEnvio.get());
    }

    @Override
    public List<DetalleEnvioToShowDto> getAllDetalleEnvio() {

        List<DetalleEnvio> detallesEnvio = detalleEnvioRepository.findAll();

        if(detallesEnvio.isEmpty())
            throw new NotFoundException("No se ha encontrado el detalle de envio");

        List<DetalleEnvioToShowDto> detalle = new ArrayList<>();

        detallesEnvio.forEach( d -> {
            DetalleEnvioToShowDto d2 = detalleEnvioMapper.detalleEnvioEntityToDetalleEnvioToShowDto(d);
            detalle.add(d2);
        });

        return detalle;
    }

    @Override
    public DetalleEnvioToShowDto getDetalleEnvioByPedidoId(UUID pedido_id) {

        Optional<DetalleEnvio> detalle = detalleEnvioRepository.findByPedidoId(pedido_id);

        if(detalle.isEmpty())
            throw new NotFoundException("No se ha encontrado el detalle de envio para ese pedido");

        return detalleEnvioMapper.detalleEnvioEntityToDetalleEnvioToShowDto(detalle.get());
    }

    @Override
    public List<DetalleEnvioToShowDto> getDetalleEnvioByTransportadora(String transportadora) {

        List<DetalleEnvio> detalles = detalleEnvioRepository.findByTransportadoraContainingIgnoreCase(transportadora);

        if(detalles.isEmpty())
            throw new NotFoundException("No se ha encontrado el detalle de envio para la transportadora " + transportadora);

        List<DetalleEnvioToShowDto> detalle = new ArrayList<>();

        detalles.forEach( d -> {
            DetalleEnvioToShowDto d2 = detalleEnvioMapper.detalleEnvioEntityToDetalleEnvioToShowDto(d);
            detalle.add(d2);
        });

        return detalle;
    }

    @Override
    public DetalleEnvioToShowDto saveDetalleEnvio(DetalleEnvioToSaveDto detalleEnvioToSaveDto) {
        DetalleEnvio detalleEnvio = detalleEnvioMapper.detalleEnvioToSaveDtoToDetalleEnvioEntity(detalleEnvioToSaveDto);

        Optional<Pedido> pedido = pedidoRepository.findById(detalleEnvioToSaveDto.pedido().id());
        if(  pedido.isEmpty() )
            throw new NotFoundException("No se ha encontrado el pedido");

        detalleEnvio.setPedido(pedido.get());

        DetalleEnvio detalleGuardado = detalleEnvioRepository.save(detalleEnvio);
        return detalleEnvioMapper.detalleEnvioEntityToDetalleEnvioToShowDto(detalleGuardado);
    }

    @Override
    public DetalleEnvioToShowDto updateDetalleEnvioById(UUID id, DetalleEnvioToSaveDto detalleEnvioToSaveDto) {

        Optional<DetalleEnvio> detalleEnvio = detalleEnvioRepository.findById(id);

        if(detalleEnvio.isEmpty())
            throw new NotFoundException("No se ha encontrado el detalle de envio con ese id " + id);

        if(detalleEnvioToSaveDto.numeroGuia() != null )
            detalleEnvio.get().setNumeroGuia(detalleEnvioToSaveDto.numeroGuia());

        if(detalleEnvioToSaveDto.direccion() != null)
            detalleEnvio.get().setDireccion(detalleEnvioToSaveDto.direccion());

        if(detalleEnvioToSaveDto.transportadora() != null)
            detalleEnvio.get().setTransportadora(detalleEnvioToSaveDto.transportadora());

        DetalleEnvio detalleGuardado = detalleEnvioRepository.save(detalleEnvio.get());

        return detalleEnvioMapper.detalleEnvioEntityToDetalleEnvioToShowDto(detalleGuardado);
    }

    @Override
    public void deleteDetalleEnvioById(UUID id) {

        Optional<DetalleEnvio> detalleEnvio = detalleEnvioRepository.findById(id);

        if(detalleEnvio.isEmpty())
            throw new NotFoundException("No se ha encontrado el detalle de envio con ese id " + id);

        detalleEnvioRepository.deleteById(id);

    }
}
