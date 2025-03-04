package io.github.mateusbosquetti.picpayapi.repository;

import io.github.mateusbosquetti.picpayapi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findUserByDocument(String document);
    public Optional<User> findUserById(Long id);
}
