package com.dantesoft.sireauthservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dantesoft.sireauthservice.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    public Optional<Role> findByNameContainingIgnoreCase(String name);
}
