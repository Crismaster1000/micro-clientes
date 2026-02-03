package com.stephano.microclientes.dto;

import com.stephano.microclientes.entity.Genero;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class ClienteRequest {

    @NotBlank
    private String nombre;

    @NotNull
    private Genero genero;

    @NotNull
    private Integer edad;

    @NotBlank
    private String identificacion;

    @NotBlank
    private String direccion;

    @NotBlank
    private String telefono;

    @NotBlank
    private String clienteId;

    @NotBlank
    private String contrasena;

    @NotNull
    private Boolean estado;

}
