package com.tatara.reward.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectNotExistException.class)
    public ResponseEntity<?> handleException(ObjectNotExistException objectNotExistException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(objectNotExistException.getMessage());
    }

    @ExceptionHandler(NotEnoughQuantityException.class)
    public ResponseEntity<?> handleException(NotEnoughQuantityException notEnoughQuantityException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(notEnoughQuantityException.getMessage());
    }
}
