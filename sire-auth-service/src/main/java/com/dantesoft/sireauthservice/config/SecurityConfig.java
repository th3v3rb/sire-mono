package com.dantesoft.sireauthservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dantesoft.sireauthservice.controller.JwtAuthenticationFilter;
import com.dantesoft.sireauthservice.service.UserDetailsServiceImp;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	final UserDetailsServiceImp userDetailsServiceImp;
	final JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//@formatter:off
		return http.csrf(csrf -> csrf.disable())
				   .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				   .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				   .authorizeHttpRequests(
				   	req -> req.requestMatchers(this.getPublicRoutes()).permitAll()
				   			  .anyRequest().authenticated()).userDetailsService(userDetailsServiceImp)
				   .exceptionHandling(
				   		e -> e.accessDeniedHandler((request, response, accessDeniedException) -> response.setStatus(403))
				   			  .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
				   .logout(l -> l.disable())
				   .formLogin(form -> form.disable())
				   .build();
		//@formatter:on
	}

	private String[] getPublicRoutes() {
		return new String[] { "api/v1/auth/**", };
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

}
