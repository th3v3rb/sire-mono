package com.dantesoft.sireproductservice.payload.request.item;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import com.dantesoft.sireproductservice.domain.UnitMeasure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateItemRequest {
	private String name;
	private String description;
	private String imageUrl;
	private String barCode;
	private String warehouseLocation;
	private UnitMeasure unitMeasure;
	private BigDecimal quantiy;
	private BigDecimal buyPrice;
	private BigDecimal sellPrice;
	private List<UUID> categoriesIds;
	private UUID brandUuid;
	private boolean active;
}
