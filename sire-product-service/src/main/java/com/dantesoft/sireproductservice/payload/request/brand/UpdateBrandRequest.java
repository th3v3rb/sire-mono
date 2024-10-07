package com.dantesoft.sireproductservice.payload.request.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandRequest {
	private String name;
	private String description;
	private boolean active;

}
