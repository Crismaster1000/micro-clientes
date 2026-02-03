package com.stephano.microclientes.messaging;


import com.stephano.microclientes.entity.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteEventPublisher {

    public void publicarClienteCreado(Cliente c) { }
    public void publicarClienteActualizado(Cliente c) { }
    public void publicarClienteEliminado(String clienteId) { }

}
