package io.github.mateusbosquetti.picpayapi.model.dto.request;

import io.github.mateusbosquetti.picpayapi.model.entity.User;
import io.github.mateusbosquetti.picpayapi.model.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record UserRequestDTO(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String document,
        @NotNull BigDecimal balance,
        @NotBlank @Email String email,
        @NotBlank String type,
        @NotBlank String password
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
