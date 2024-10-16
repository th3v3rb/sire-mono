package com.dantesoft.sireauthservice.service;

import org.springframework.stereotype.Service;

import com.dantesoft.sireauthservice.entity.Role;
import com.dantesoft.sireauthservice.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

    final RoleRepository roleRepo;


    /**
     * Handle search the role on the database, ignore case
     * 
     * @param role
     * @return
     */
    public Role getRoleByName(String role) {
        var search = this.roleRepo.findByNameContainingIgnoreCase(role);

        if (search.isEmpty()) {
            throw new RuntimeException("Role not found");
        }

        return search.get();
    }

}
