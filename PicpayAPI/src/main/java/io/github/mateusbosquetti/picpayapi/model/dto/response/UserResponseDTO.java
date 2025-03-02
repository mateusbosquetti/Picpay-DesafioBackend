package io.github.mateusbosquetti.picpayapi.model.dto.response;

import io.github.mateusbosquetti.picpayapi.model.enums.UserType;

import java.math.BigDecimal;

public record UserResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String document,
        String email,
        String password,
        BigDecimal balance,
        UserType userType
) {
}
