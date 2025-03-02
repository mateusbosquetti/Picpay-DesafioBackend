package io.github.mateusbosquetti.picpayapi.services;

import io.github.mateusbosquetti.picpayapi.model.dto.request.UserRequestDTO;
import io.github.mateusbosquetti.picpayapi.model.entity.User;
import io.github.mateusbosquetti.picpayapi.model.enums.UserType;
import io.github.mateusbosquetti.picpayapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Usuario do tipo lojista não está autorizado");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente");
        }

    }

    public User findUserById(Long id) throws Exception {
        return repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado "));
    }

    public void saveUser (User user) {
        repository.save(user);
    }

    public User addUser(UserRequestDTO userRequestDTO) {
        User user = userRequestDTO.toEntity();
        return repository.save(user);
    }

    public List<User> getUser() {
        return repository.findAll();
    }
}
