package io.github.mateusbosquetti.picpayapi.services;

import io.github.mateusbosquetti.picpayapi.infra.exception.InsufficientBalanceException;
import io.github.mateusbosquetti.picpayapi.infra.exception.InvalidUserTypeException;
import io.github.mateusbosquetti.picpayapi.infra.exception.UserNotFoundException;
import io.github.mateusbosquetti.picpayapi.model.dto.request.UserRequestDTO;
import io.github.mateusbosquetti.picpayapi.model.dto.response.UserResponseDTO;
import io.github.mateusbosquetti.picpayapi.model.entity.User;
import io.github.mateusbosquetti.picpayapi.model.enums.UserType;
import io.github.mateusbosquetti.picpayapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new InvalidUserTypeException("Usuario do tipo lojista não está autorizado a fazer transições");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Saldo insuficiente");
        }

    }

    public User findUserById(Long id) {
        return repository.findUserById(id).orElseThrow(() -> new UserNotFoundException("Usuário de ID " + id + " não encontrado!"));
    }

    public void saveUser(User user) {
        repository.save(user);
    }

    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
        User user = userRequestDTO.toEntity();
        repository.save(user);
        return user.toDTO();
    }

    public List<UserResponseDTO> getUser() {
        return repository.findAll().stream().map(User::toDTO).collect(Collectors.toList());
    }
}
