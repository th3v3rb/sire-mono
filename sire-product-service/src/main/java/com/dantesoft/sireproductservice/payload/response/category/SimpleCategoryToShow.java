package com.dantesoft.sireproductservice.payload.response.category;

import java.time.LocalDateTime;

public interface SimpleCategoryToShow {
	String getName();

	boolean isActive();

	LocalDateTime getCreationTime();

	LocalDateTime getModificationTime();
}
