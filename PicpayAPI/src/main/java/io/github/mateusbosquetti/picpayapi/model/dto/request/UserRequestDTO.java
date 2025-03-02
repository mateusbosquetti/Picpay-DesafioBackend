package io.github.mateusbosquetti.picpayapi.model.dto.request;

import io.github.mateusbosquetti.picpayapi.model.entity.User;
import io.github.mateusbosquetti.picpayapi.model.enums.UserType;

import java.math.BigDecimal;

public record UserRequestDTO(
        String firstName,
        String lastName,
        String document,
        BigDecimal balance,
        String email,
        String type,
        String password
        ) {
    public User toEntity() {
        return User.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .document(this.document)
                .balance(this.balance)
                .email(this.email)
                .password(this.password)
                .userType(UserType.valueOf(type))
                .build();
    }
}
