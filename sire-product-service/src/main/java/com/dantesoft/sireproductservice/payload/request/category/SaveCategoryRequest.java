package com.dantesoft.sireproductservice.payload.request.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveCategoryRequest {
	private String name;
}
