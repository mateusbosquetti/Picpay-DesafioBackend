package io.github.mateusbosquetti.picpayapi.model.dto.request;

import io.github.mateusbosquetti.picpayapi.model.entity.Transaction;
import io.github.mateusbosquetti.picpayapi.services.UserService;

import javax.sound.midi.Receiver;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionRequestDTO (
        BigDecimal value,
        Long senderId,
        Long receiverId
) {
    public Transaction toEntity(UserService userService) throws Exception {
        return Transaction.builder()
                .amount(this.value)
                .sender(userService.findUserById(this.senderId))
                .receiver(userService.findUserById(this.receiverId))
                .timeStamp(LocalDateTime.now())
                .build();
    }
}
