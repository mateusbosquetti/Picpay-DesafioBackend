package io.github.mateusbosquetti.picpayapi.model.entity;

import io.github.mateusbosquetti.picpayapi.model.dto.response.UserResponseDTO;
import io.github.mateusbosquetti.picpayapi.model.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String document;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    @OneToMany(mappedBy = "sender")
    List<Transaction> senderTransiction;

    @OneToMany(mappedBy = "receiver")
    List<Transaction> receiverTransiction;

    public UserResponseDTO toDTO() {
        return new UserResponseDTO(
                this.id,
                this.firstName,
                this.lastName,
                this.document,
                this.email,
                this.password,
                this.balance,
                this.userType
        );
    }
}
