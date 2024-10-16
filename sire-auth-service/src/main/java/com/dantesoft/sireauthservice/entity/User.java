package com.dantesoft.sireauthservice.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    private String password;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime creationTime;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime modificationTime;

    @Column(name = "account_expires_at")
    private LocalDateTime accountExpiredAt;

    @Column(name = "account_locked_at")
    private LocalDate accountLockedAt;

    @Column(name = "credentials_expired_at")
    private LocalDateTime credentialsExpiredAt;

    @Column(name = "enabled")
    private boolean isEnabled;

    @Column(name = "email_verified_at")
    private LocalDateTime emailVerifiedAt;
    
    @ManyToOne
    private Enterprise enterprise;

    @ManyToMany
    @JoinTable(name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountExpiredAt == null || accountExpiredAt.isAfter(LocalDateTime.now());
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountLockedAt == null;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsExpiredAt == null || credentialsExpiredAt.isAfter(LocalDateTime.now());
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}