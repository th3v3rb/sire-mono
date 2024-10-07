package com.dantesoft.sireauthservice.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dantesoft.sireauthservice.entity.Token;
import com.dantesoft.sireauthservice.entity.User;
import com.dantesoft.sireauthservice.payload.request.CreateUserRequest;
import com.dantesoft.sireauthservice.payload.request.UserCredentials;
import com.dantesoft.sireauthservice.payload.response.AuthenticationResponse;
import com.dantesoft.sireauthservice.payload.response.UserDto;
import com.dantesoft.sireauthservice.repository.TokenRepository;
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
	final TokenRepository tokenRepository;

	/**
	 * To register a new user
	 * 
	 * @param request
	 * @return
	 */
	public AuthenticationResponse register(CreateUserRequest request) {
		if (!request.getPassword().equals(request.getPasswordConfirmation())) {
			log.warn("Passwords do not match");
			return new AuthenticationResponse(null, "Passwords do not match");
		}

		if (userRepo.findByEmail(request.getEmail()).isPresent()) {
			log.warn("Email already taken");
			return new AuthenticationResponse(null, "Email already taken");
		}

		User user = User.builder()
				.username(request.getUsername())
				.password(passwordEnconder.encode(request.getPassword()))
				.roles(List.of(this.roleService.getRoleByName("GUEST")))
				.isEnabled(true)
				.email(request.getEmail()).build();

		user = userRepo.save(user);
		String jwt = jwtService.generateToken(user);
		saveUserToken(jwt, user);

		return new AuthenticationResponse(jwt, "User registration was successful");
	}

	/**
	 * To login and authenticate a user
	 * 
	 * 
	 * @param request
	 * @return
	 */
	public AuthenticationResponse authenticate(UserCredentials request) {
		var usernameAndPassword = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
		authenticationManager.authenticate(usernameAndPassword);

		User user = userRepo.findByEmail(request.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		String jwt = jwtService.generateToken(user);
		// revokeAllTokenByUser(user);
		
		saveUserToken(jwt, user);

		return new AuthenticationResponse(jwt, "User login was successful");
	}

	public UserDto validate(String urlToken) {
		return UserDto.builder()
				.id(jwtService.extractUserId(urlToken))
				.email(jwtService.extractEmail(urlToken))
				.name(jwtService.extractUsername(urlToken))
				.roles(jwtService.extractRoles(urlToken))
				.mailVerifiedAt(jwtService.extractMailVerifiedAt(urlToken))
				.build();
	}

	/**
	 * Revoke all tokens searching by user id
	 * 
	 * @param user
	 */
	private void revokeAllTokenByUser(User user) {
		List<Token> validTokens = tokenRepository.findAllTokensByUser(user.getId());

		if (validTokens.isEmpty()) {
			return;
		}

		validTokens.forEach(t -> t.setLoggedOut(true));

		tokenRepository.saveAll(validTokens);
	}

	/**
	 * Store new token on database
	 * 
	 * @param jwt
	 * @param user
	 */
	private void saveUserToken(String jwt, User user) {
		Token token = new Token();
		token.setToken(jwt);
		token.setLoggedOut(false);
		token.setUser(user);
		tokenRepository.save(token);
	}
}
