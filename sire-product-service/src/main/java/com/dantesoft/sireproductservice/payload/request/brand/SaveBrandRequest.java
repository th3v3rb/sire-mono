package com.dantesoft.sireproductservice.payload.request.brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveBrandRequest {

	@NotBlank(message = "The field 'name' cant be blank")
	@NotNull(message = "The field 'name' its required")
	private String name;

	@NotBlank(message = "The field 'description' cant be blank")
	private String description;

}
