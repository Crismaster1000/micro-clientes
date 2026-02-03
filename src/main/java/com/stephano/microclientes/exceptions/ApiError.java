package com.stephano.microclientes.exceptions;

import lombok.Builder;

import java.time.Instant;

public record ApiError(

        Instant timestamp,
        int status,
        String error,
        String message,
        String path
) {
}
