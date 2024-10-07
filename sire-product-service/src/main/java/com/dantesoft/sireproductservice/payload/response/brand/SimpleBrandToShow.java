package com.dantesoft.sireproductservice.payload.response.brand;

import java.time.LocalDateTime;
import java.util.UUID;

public interface SimpleBrandToShow {
	UUID getUuid();

	String getName();

	String getDescription();

	boolean isActive();

	LocalDateTime getCreationTime();

	LocalDateTime getModificationTime();
}
