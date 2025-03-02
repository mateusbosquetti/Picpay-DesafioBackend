package io.github.mateusbosquetti.picpayapi.model.dto.response;

import io.github.mateusbosquetti.picpayapi.model.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponseDTO(
        Long id,
        BigDecimal amount,
        UserResponseDTO sender,
        UserResponseDTO receiver,
        LocalDateTime timeStamp
) {
}
