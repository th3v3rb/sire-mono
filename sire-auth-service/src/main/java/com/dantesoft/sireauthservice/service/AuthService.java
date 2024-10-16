package com.dantesoft.sireauthservice.service;

import java.util.List;

import com.dantesoft.sireauthservice.entity.Role;
import com.dantesoft.sireauthservice.exception.elements.LoginException;
import com.dantesoft.sireauthservice.exception.elements.RegisterException;
import com.dantesoft.sireauthservice.payload.response.RegistrationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dantesoft.sireauthservice.entity.User;
import com.dantesoft.sireauthservice.payload.request.CreateUserRequest;
import com.dantesoft.sireauthservice.payload.request.UserCredentials;
import com.dantesoft.sireauthservice.payload.response.AuthenticationResponse;
import com.dantesoft.sireauthservice.payload.response.UserDto;
import com.dantesoft.sireauthservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    final AuthenticationManager authenticationManager;
    final PasswordEncoder passwordEnconder;
    final UserRepository userRepo;
    final RoleService roleService;
    final JwtService jwtService;

    /**
     * @param request create user request #{@see CreateUserRequest.java}
     * @return the operation status
     */
    public RegistrationResponse register(CreateUserRequest request) {
        if (!request.getPassword().equals(request.getPasswordConfirmation())) {
            log.warn("Passwords do not match");
            throw new RegisterException("Passwords do not match");
        }

        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            log.warn("Email already taken");
            throw new RegisterException("Email already taken");
        }

        final User user = User.builder()
                .username(request.getUsername())
                .password(passwordEnconder.encode(request.getPassword()))
                .roles(List.of(this.roleService.getRoleByName("GUEST")))
                .isEnabled(true)
                .email(request.getEmail()).build();

        final UserDto userDto = UserDto.builder()
                .name(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles().stream().map(Role::getName).toArray(String[]::new))
                .build();

        return new RegistrationResponse("User successfully registered", userDto);
    }

    /**
     * To log in and authenticate a user
     *
     * @param request http request
     * @return a message with the operation status
     */
    public AuthenticationResponse authenticate(UserCredentials request) {
        var usernameAndPassword = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        authenticationManager.authenticate(usernameAndPassword);

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if(!user.isCredentialsNonExpired()) {
            throw new LoginException("The credentials are expired");
        }

        if(!user.isAccountNonExpired()) {
            throw new LoginException("The account are expired");
        }

        if(!user.isEnabled()) {
            throw new LoginException("The account are disabled");
        }

        if(user.getEmailVerifiedAt() == null) {
            throw new LoginException("This email must be verified firts");
        }

        String jwt = jwtService.generateToken(user);

        return new AuthenticationResponse(jwt, "User login was successful");
    }

    /**
     * Validates the jwt token
     *
     * @param urlToken token
     * @return user information
     */
    public UserDto validate(String urlToken) {
        return UserDto.builder()
                .id(jwtService.extractUserId(urlToken))
                .email(jwtService.extractEmail(urlToken))
                .name(jwtService.extractUsername(urlToken))
                .roles(jwtService.extractRoles(urlToken))
                .mailVerifiedAt(jwtService.extractMailVerifiedAt(urlToken))
                .build();
    }
}
