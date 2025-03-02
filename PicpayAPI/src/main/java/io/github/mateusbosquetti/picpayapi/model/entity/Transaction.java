package io.github.mateusbosquetti.picpayapi.model.entity;

import io.github.mateusbosquetti.picpayapi.model.dto.response.TransactionResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    @Column(nullable = false)
    private LocalDateTime timeStamp;

    public TransactionResponseDTO toDTO() {
        return new TransactionResponseDTO(
                this.id,
                this.amount,
                this.sender.toDTO(),
                this.receiver.toDTO(),
                this.timeStamp
        );
    }
}
