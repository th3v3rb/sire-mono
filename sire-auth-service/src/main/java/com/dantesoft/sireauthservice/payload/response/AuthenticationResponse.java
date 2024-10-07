package com.dantesoft.sireauthservice.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String message;
}
