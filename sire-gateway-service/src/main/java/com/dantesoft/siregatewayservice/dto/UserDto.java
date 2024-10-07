package com.dantesoft.siregatewayservice.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class UserDto {
    private UUID id;
    private String email;
    private String[] roles;
    private LocalDateTime mailVerifiedAt;
}
