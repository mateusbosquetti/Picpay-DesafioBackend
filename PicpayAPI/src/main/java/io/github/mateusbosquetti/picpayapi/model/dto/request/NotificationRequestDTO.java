package io.github.mateusbosquetti.picpayapi.model.dto.request;

public record NotificationRequestDTO (
        String email,
        String message
) {
}
