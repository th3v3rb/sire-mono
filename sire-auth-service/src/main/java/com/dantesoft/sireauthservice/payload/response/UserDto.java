package com.dantesoft.sireauthservice.payload.response;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private UUID id;
    private LocalDateTime mailVerifiedAt;
    private String name;
    private String[] roles;
    private String email; 
}
