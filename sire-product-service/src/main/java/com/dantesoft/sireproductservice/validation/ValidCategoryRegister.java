package com.dantesoft.sireproductservice.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidCategoryRegister {
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
}
