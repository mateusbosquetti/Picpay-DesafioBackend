package io.github.mateusbosquetti.picpayapi.model.dto.response;

import org.springframework.http.HttpStatus;

public record ExceptionResponseDTO (
        String message,
        HttpStatus statusCode
) {
}
