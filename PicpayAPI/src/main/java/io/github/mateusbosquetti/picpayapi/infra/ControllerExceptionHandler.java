package io.github.mateusbosquetti.picpayapi.infra;

import io.github.mateusbosquetti.picpayapi.infra.exception.InsufficientBalanceException;
import io.github.mateusbosquetti.picpayapi.infra.exception.InvalidUserTypeException;
import io.github.mateusbosquetti.picpayapi.infra.exception.UnauthorizedTransactionException;
import io.github.mateusbosquetti.picpayapi.infra.exception.UserNotFoundException;
import io.github.mateusbosquetti.picpayapi.model.dto.response.ExceptionResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponseDTO> threatDuplicateEntry(DataIntegrityViolationException exception) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO("Informações únicas ja foram cadastradas!", HttpStatus.valueOf(400), exception.getClass());
        return new ResponseEntity<>(exceptionResponseDTO, exceptionResponseDTO.statusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDTO> threatValidationErrors(MethodArgumentNotValidException exception) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO("Erro na validação dos campos da requisição!", HttpStatus.valueOf(400), exception.getClass());
        return new ResponseEntity<>(exceptionResponseDTO, exceptionResponseDTO.statusCode());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> threatUserNotFound(UserNotFoundException exception) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO("Erro ao buscar usuário!", HttpStatus.valueOf(404), exception.getClass());
        return new ResponseEntity<>(exceptionResponseDTO, exceptionResponseDTO.statusCode());
    }

    @ExceptionHandler(InvalidUserTypeException.class)
    public ResponseEntity<ExceptionResponseDTO> threatUserTypeError(InvalidUserTypeException exception) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO("Lojistas não podem realizar transações, apenas receber!", HttpStatus.valueOf(400), exception.getClass());
        return new ResponseEntity<>(exceptionResponseDTO, exceptionResponseDTO.statusCode());
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ExceptionResponseDTO> threatBalanceError(InsufficientBalanceException exception) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO("Saldo insuficiente!", HttpStatus.valueOf(400), exception.getClass());
        return new ResponseEntity<>(exceptionResponseDTO, exceptionResponseDTO.statusCode());
    }

    @ExceptionHandler(UnauthorizedTransactionException.class)
    public ResponseEntity<ExceptionResponseDTO> threatForbidden(UnauthorizedTransactionException exception) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO("Transação não autorizada!", HttpStatus.valueOf(403), exception.getClass());
        return new ResponseEntity<>(exceptionResponseDTO, exceptionResponseDTO.statusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> threatGeneralError(Exception exception) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO("Erro interno!", HttpStatus.valueOf(500), exception.getClass());
        return new ResponseEntity<>(exceptionResponseDTO, exceptionResponseDTO.statusCode());
    }

}
