package io.github.mateusbosquetti.picpayapi.controller;

import io.github.mateusbosquetti.picpayapi.model.dto.request.UserRequestDTO;
import io.github.mateusbosquetti.picpayapi.model.dto.response.UserResponseDTO;
import io.github.mateusbosquetti.picpayapi.model.entity.User;
import io.github.mateusbosquetti.picpayapi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService service;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser (@RequestBody @Validated UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(service.addUser(userRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return new ResponseEntity<>(service.getUser(), HttpStatus.OK);
    }

}
