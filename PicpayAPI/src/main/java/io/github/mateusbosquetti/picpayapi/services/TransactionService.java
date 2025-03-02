package io.github.mateusbosquetti.picpayapi.services;

import io.github.mateusbosquetti.picpayapi.infra.exception.UnauthorizedTransactionException;
import io.github.mateusbosquetti.picpayapi.model.dto.request.TransactionRequestDTO;
import io.github.mateusbosquetti.picpayapi.model.dto.response.TransactionResponseDTO;
import io.github.mateusbosquetti.picpayapi.model.entity.Transaction;
import io.github.mateusbosquetti.picpayapi.model.entity.User;
import io.github.mateusbosquetti.picpayapi.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
@AllArgsConstructor
public class TransactionService {

    private TransactionRepository repository;
    private UserService userService;

    private RestTemplate restTemplate;
    private NotificationService notificationService;

    public TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO) {

        Transaction transaction = transactionRequestDTO.toEntity(userService);
        User sender = transaction.getSender();
        User receiver = transaction.getReceiver();

        userService.validateTransaction(sender, transactionRequestDTO.value());

        authorizeTransaction(sender, transactionRequestDTO.value());

        sender.setBalance(
                sender.getBalance().subtract(transaction.getAmount())
        );

        receiver.setBalance(
                receiver.getBalance().add(transaction.getAmount())
        );

        repository.save(transaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        notificationService.sendNotification(sender, "ransação realizada com sucesso");
        notificationService.sendNotification(receiver, "Transação realizada com sucesso");

        return transaction.toDTO();

    }

    private void authorizeTransaction(User sender, BigDecimal value) {
        try {
            ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);
        } catch (Exception e) {
            throw new UnauthorizedTransactionException();
        }
    }

}
