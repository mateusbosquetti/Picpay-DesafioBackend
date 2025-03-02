package io.github.mateusbosquetti.picpayapi.infra;

import io.github.mateusbosquetti.picpayapi.model.dto.response.ExceptionResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponseDTO> threatDuplicateEntry(DataIntegrityViolationException exception) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO("Usuário ja cadastrado", HttpStatus.valueOf(400));
        return new ResponseEntity<>(exceptionResponseDTO, exceptionResponseDTO.statusCode());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(EntityNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> threatDuplicateEntry(Exception exception) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(exception.getMessage(), HttpStatus.valueOf(500));
        return new ResponseEntity<>(exceptionResponseDTO, exceptionResponseDTO.statusCode());
    }

}
