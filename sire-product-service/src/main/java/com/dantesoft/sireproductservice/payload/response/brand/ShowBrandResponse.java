package com.dantesoft.sireproductservice.payload.response.brand;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class ShowBrandResponse {
	private UUID uuid;
	private String name;
	private String description;
	private LocalDateTime creationTime;
	private LocalDateTime modificationTime;
}
