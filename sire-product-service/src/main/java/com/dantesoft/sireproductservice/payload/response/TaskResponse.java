package com.dantesoft.sireproductservice.payload.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TaskResponse {
	private String message;
	private String description;
}
