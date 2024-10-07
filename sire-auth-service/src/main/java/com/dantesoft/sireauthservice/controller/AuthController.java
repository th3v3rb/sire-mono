package com.dantesoft.sireauthservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dantesoft.sireauthservice.payload.request.CreateUserRequest;
import com.dantesoft.sireauthservice.payload.request.UserCredentials;
import com.dantesoft.sireauthservice.payload.response.AuthenticationResponse;
import com.dantesoft.sireauthservice.service.AuthService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

	final AuthService authService;

	@PostMapping("/v1/auth/register")
	ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid CreateUserRequest request) {
		return ResponseEntity.ok(authService.register(request));
	}

	@PostMapping("/v1/auth/login")
	ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid UserCredentials request) {
		return ResponseEntity.ok(authService.authenticate(request));
	}

	@PostMapping("/v1/auth/validate/{token}")
	ResponseEntity<?> validate(@PathParam(value = "token") String token) {
		return ResponseEntity.ok(authService.validate(token));
	}
}
