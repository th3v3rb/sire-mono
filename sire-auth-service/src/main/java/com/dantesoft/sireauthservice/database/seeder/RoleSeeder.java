package com.dantesoft.sireauthservice.database.seeder;

import java.util.List;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dantesoft.sireauthservice.entity.Role;
import com.dantesoft.sireauthservice.repository.RoleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class RoleSeeder {
    final RoleRepository roleRepo;
    final JdbcTemplate databaseTemplate;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        populateRolesTable();
    }

    private void populateRolesTable() {
        String counterQuery = "SELECT id, name FROM ROLES;";

        List<Role> roles = databaseTemplate.query(counterQuery, (resultSet, rowNum) -> null);

        if (roles.isEmpty()) {
            var guestRole = Role.builder()
                    .name("Guest")
                    .build();

            roleRepo.save(guestRole);

            var adminRole = Role.builder().name("Admin").build();

            roleRepo.save(adminRole);

            log.info("Roles table are seed");
        } else {
            log.info("Database has roles registered, seed not been run");
        }
    }
}
