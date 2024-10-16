package com.dantesoft.sireauthservice.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dantesoft.sireauthservice.entity.Role;
import com.dantesoft.sireauthservice.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtService {
    /**
     * provided by config
     */
    @Value("${jwt.secret}")
    String secret;

    /**
     * provided by config
     */
    @Value("${jwt.expiration}")
    long expirationTime;


    /**
     * Gets all token claims information
     * 
     * @param token
     * @return
     */
    public Claims getAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Validates the token comparing the user
     * provided and the token subject
     * 
     * @param token
     * @param user
     * @return
     */
    public boolean isValid(String token, UserDetails user) {
        String username = extractUsername(token);
        return username.equals(user.getUsername()) && isTokenNotExpired(token);
    }

    /**
     * Generate a token adding the user information in its
     * 
     * @param user
     * @return
     */
    public String generateToken(User user) {
        String token = Jwts.builder()
                .signWith(getSignInKey())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .subject(user.getUsername())
                .claim("id", user.getId())
                .claim("email", user.getEmail())
                .claim("mail_verified_at", user.getEmailVerifiedAt())
                .claim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .compact();

        return token;
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = getAllClaims(token);
        return resolver.apply(claims);
    }

    /**
     * Get the user id by token
     * 
     * @param token
     * @return
     */
    public UUID extractUserId(String token) {
        return extractClaim(token, resolve -> resolve.get("id", UUID.class));
    }

    /**
     * Get the username by token
     * 
     * @param token
     * @return
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Get the date when the mail has verified
     * 
     * @param token
     * @return
     */
    public LocalDateTime extractMailVerifiedAt(String token) {
        return extractClaim(token, resolve -> resolve.get("mail_verified_at", LocalDateTime.class));
    }

    /**
     * Get the email by token
     * 
     * @param token
     * @return
     */
    public String extractEmail(String token) {
        return extractClaim(token, resolve -> resolve.get("email", String.class));
    }

    /**
     * Get the list of roles by token
     * 
     * @param token
     * @return
     */
    public String[] extractRoles(String token) {
        return extractClaim(token, resolve -> resolve.get("roles", String[].class));
    }

    /**
     * Generate a signature key for on secret base 
     * 
     * @return
     */
    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Verify its the token not expired
     * 
     * @param token
     * @return
     */
    private boolean isTokenNotExpired(String token) {
        return !this.getAllClaims(token).getExpiration().before(new Date());
    }

}
