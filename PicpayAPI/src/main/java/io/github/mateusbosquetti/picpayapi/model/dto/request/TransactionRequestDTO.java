package io.github.mateusbosquetti.picpayapi.model.dto.request;

import io.github.mateusbosquetti.picpayapi.model.entity.Transaction;
import io.github.mateusbosquetti.picpayapi.services.UserService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import javax.sound.midi.Receiver;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionRequestDTO (
        @NotNull @Positive BigDecimal value,
        @NotNull @Positive Long senderId,
        @NotNull @Positive Long receiverId
) {
    public Transaction toEntity(UserService userService) {
        return Transaction.builder()
                .amount(this.value)
                .sender(userService.findUserById(this.senderId))
                .receiver(userService.findUserById(this.receiverId))
                .timeStamp(LocalDateTime.now())
                .build();
    }
}
