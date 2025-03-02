package io.github.mateusbosquetti.picpayapi.repository;

import io.github.mateusbosquetti.picpayapi.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
