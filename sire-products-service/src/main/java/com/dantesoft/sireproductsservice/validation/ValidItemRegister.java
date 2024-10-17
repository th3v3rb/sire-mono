package com.dantesoft.sireproductsservice.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidItemRegister {
    @NotBlank
    @Size(min = 3)
    private String name;
    @Size(min = 3)
    private String description;
    @NotBlank
    @Size(min = 3)
    private BigDecimal price;
    @NotBlank
    private UUID category;
}
