package com.stephano.microclientes.messaging.events;

import java.io.Serializable;
import java.time.Instant;

public class ClienteEvent implements Serializable{

    String type;
    Instant occurredAt;
    String clienteId;
    String nombre;
    String identificacion;
    Boolean estado;

    public ClienteEvent() {}

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Instant getOccurredAt() { return occurredAt; }
    public void setOccurredAt(Instant occurredAt) { this.occurredAt = occurredAt; }

    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }


}
