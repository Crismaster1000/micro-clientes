package com.stephano.microclientes.messaging.events;

import java.time.Instant;

public record ClienteEvent(

        String type,
        Instant occurredAt,
        String clienteId,
        String nombre,
        String identificacion,
        Boolean estado

) {
}
