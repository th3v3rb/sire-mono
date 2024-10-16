package com.dantesoft.sireauthservice.exception.elements;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class LoginException extends RuntimeException {
    public LoginException(String message) {
        super(message);
    }
}
