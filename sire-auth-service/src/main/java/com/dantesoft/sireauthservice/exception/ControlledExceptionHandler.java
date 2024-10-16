package com.dantesoft.sireauthservice.exception;

import com.dantesoft.sireauthservice.exception.elements.LoginException;
import com.dantesoft.sireauthservice.exception.elements.RegisterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
@Slf4j
public class ControlledExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(RegisterException.class)
    public ResponseEntity<?> handleValidateException(RegisterException ex) {
        log.warn("Register exception handled", ex);

        var body = Map.of("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<?> handleValidateException(LoginException ex) {
        log.warn("Login exception handled",ex);

        var body = Map.of("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }


}
