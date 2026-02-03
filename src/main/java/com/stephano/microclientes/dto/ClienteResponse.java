package com.stephano.microclientes.dto;

import com.stephano.microclientes.entity.Genero;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class ClienteResponse {

    private String clienteId;
    private String nombre;
    private Genero genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private Boolean estado;

}
