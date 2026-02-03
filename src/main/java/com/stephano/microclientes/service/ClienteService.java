package com.stephano.microclientes.service;


import com.stephano.microclientes.entity.Cliente;
import com.stephano.microclientes.exceptions.BadRequestException;
import com.stephano.microclientes.exceptions.ClientExistException;
import com.stephano.microclientes.exceptions.NotFoundException;
import com.stephano.microclientes.messaging.ClienteEventPublisher;
import com.stephano.microclientes.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteEventPublisher clientePublisher;

    @Transactional
    public Cliente create(Cliente cliente) {

        // verificamos si el cliente ya existe
        if (clienteRepository.existsByIdentificacion(cliente.getIdentificacion())) {
            throw new ClientExistException();
        }

        // no permitimos que el cliente setee ids desde fuera
        cliente.setId(null);
        cliente.setClienteId(null);

        // guardamos para el id autogenerado
        Cliente saved = clienteRepository.save(cliente);

        // generamos el cliente id
        String generatedClienteId = "CLI-" + String.format("%06d", saved.getId());
        saved.setClienteId(generatedClienteId);

        // actualizamos
        saved = clienteRepository.save(saved);

        // evento async para micro-cuentas
        clientePublisher.publicarClienteCreado(saved);

        return saved;
    }

    public List<Cliente> list() {
        return clienteRepository.findAll();
    }


    public Cliente getById(Long id) {

        return clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
    }

    public Cliente getByClienteId(String clienteId) {
        return clienteRepository.findByClienteId(clienteId)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
    }

    @Transactional
    public Cliente update(Long id, Cliente cliente) {

        Cliente existing = getById(id);

        // Si cambia la identificacion, validamos la unicidad
        if (cliente.getIdentificacion() != null
                && !existing.getIdentificacion().equals(cliente.getIdentificacion())
                && clienteRepository.existsByIdentificacion(cliente.getIdentificacion())) {
                    throw new BadRequestException("La identificación ya existe");
        }

        // Mantener identidad (evita que el cliente cambie IDs)
        cliente.setId(existing.getId());
        cliente.setClienteId(existing.getClienteId());

        // Persistir "reemplazo" (nota: si vienen nulls, se guardarán nulls)
        Cliente updated = clienteRepository.save(cliente);

        // Evento async para micro-cuentas
        clientePublisher.publicarClienteActualizado(updated);

        return updated;
    }

    @Transactional
    public Cliente patch(Long id, Cliente partial) {

        Cliente existing = getById(id);

        // validamos identificacion
        if (partial.getIdentificacion() != null
                && !partial.getIdentificacion().equals(existing.getIdentificacion())
                && clienteRepository.existsByIdentificacion(partial.getIdentificacion())) {
            throw new BadRequestException("La identificación ya existe");
        }


        if (partial.getNombre() != null) existing.setNombre(partial.getNombre());
        if (partial.getGenero() != null) existing.setGenero(partial.getGenero());
        if (partial.getEdad() != null) existing.setEdad(partial.getEdad());
        if (partial.getIdentificacion() != null) existing.setIdentificacion(partial.getIdentificacion());
        if (partial.getDireccion() != null) existing.setDireccion(partial.getDireccion());
        if (partial.getTelefono() != null) existing.setTelefono(partial.getTelefono());

        if (partial.getContrasena() != null) existing.setContrasena(partial.getContrasena());
        if (partial.getEstado() != null) existing.setEstado(partial.getEstado());

        // aseguramos la identidad
        existing.setClienteId(existing.getClienteId());
        existing.setId(existing.getId());

        Cliente updated = clienteRepository.save(existing);

        // Evento async para micro-cuentas
        clientePublisher.publicarClienteActualizado(updated);

        return updated;
    }

    @Transactional
    public void delete(Long id) {

        if (!clienteRepository.existsById(id)) {
            throw new NotFoundException("Cliente no encontrado");
        }

        Cliente existing = getById(id);
        clienteRepository.delete(existing);

        // evento async
        clientePublisher.publicarClienteEliminado(existing.getClienteId());

    }


}
