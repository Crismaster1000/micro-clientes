package com.stephano.microclientes.messaging;


import com.stephano.microclientes.config.RabbitConfig;
import com.stephano.microclientes.entity.Cliente;
import com.stephano.microclientes.messaging.events.ClienteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ClienteEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public ClienteEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publicarClienteCreado(Cliente c) {
        System.out.println("Evento publicado: CLIENTE_CREADO -> " + c.getClienteId());
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE_CLIENTES,
                RabbitConfig.ROUTING_KEY_CLIENTES,
                buildEvent("CLIENTE_CREADO", c)
        );


    }

    public void publicarClienteActualizado(Cliente c) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE_CLIENTES,
                RabbitConfig.ROUTING_KEY_CLIENTES,
                buildEvent("CLIENTE_ACTUALIZADO", c)
        );
    }

    public void publicarClienteEliminado(String clienteId) {
        ClienteEvent event = new ClienteEvent();
        event.setType("CLIENTE_ELIMINADO");
        event.setOccurredAt(Instant.now());
        event.setClienteId(clienteId);

        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE_CLIENTES,
                RabbitConfig.ROUTING_KEY_CLIENTES,
                event
        );
    }

    private ClienteEvent buildEvent(String type, Cliente c) {
        ClienteEvent event = new ClienteEvent();
        event.setType(type);
        event.setOccurredAt(Instant.now());
        event.setClienteId(c.getClienteId());
        event.setNombre(c.getNombre());
        event.setIdentificacion(c.getIdentificacion());
        event.setEstado(c.getEstado());
        return event;
    }

}
