package com.dantesoft.sireauthservice.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String REQUEST_VALIDATION_ERRORS = "Request validation errors";
    private static final String RESOURCE_NOT_FOUND = "Resource not found";
    private static final String INTERNAL_SERVER_ERROR = "Internal server error";

    // Handle validation errors
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Map<String, String>> details = new ArrayList<>();

        // Recorrer los errores de campo
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            Map<String, String> errorDetail = new HashMap<>();
            errorDetail.put("field", error.getField());
            errorDetail.put("message", error.getDefaultMessage());
            details.add(errorDetail);
        }

        // Crear el mapa de respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("message", REQUEST_VALIDATION_ERRORS);
        response.put("details", details);

        // Retornar la respuesta con los detalles
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Handle validation errors
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
            AuthenticationException ex) {
    	ex.printStackTrace();
    	
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Bad credetials");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Handle resource not found errors
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundExceptions(ResponseStatusException ex) {
        ErrorResponse errorResponse = new ErrorResponse(RESOURCE_NOT_FOUND, List.of(ex.getReason()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle generic server errors
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(INTERNAL_SERVER_ERROR, List.of(ex.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // ErrorResponse class
    public static class ErrorResponse {
        private String message;
        private List<String> details;

        public ErrorResponse(String message, List<String> details) {
            this.message = message;
            this.details = details;
        }

        public String getMessage() {
            return message;
        }

        public List<String> getDetails() {
            return details;
        }

        public static ErrorResponse builder(String message, List<String> details) {
            return new ErrorResponse(message, details);
        }
    }
}
