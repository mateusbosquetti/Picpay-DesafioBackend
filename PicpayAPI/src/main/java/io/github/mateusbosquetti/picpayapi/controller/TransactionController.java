package io.github.mateusbosquetti.picpayapi.controller;

import io.github.mateusbosquetti.picpayapi.model.dto.request.TransactionRequestDTO;
import io.github.mateusbosquetti.picpayapi.model.entity.Transaction;
import io.github.mateusbosquetti.picpayapi.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {

    private TransactionService service;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) throws Exception {
        return new ResponseEntity<>(service.createTransaction(transactionRequestDTO), HttpStatus.CREATED);
    }

}
