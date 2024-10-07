package com.dantesoft.sireproductservice.services.item;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.dantesoft.sireproductservice.payload.request.item.SaveItemRequest;
import com.dantesoft.sireproductservice.payload.request.item.UpdateItemRequest;
import com.dantesoft.sireproductservice.payload.response.TaskResponse;
import com.dantesoft.sireproductservice.payload.response.item.SimpleItemToList;
import com.dantesoft.sireproductservice.payload.response.item.SimpleItemToShow;

public interface ItemService {

	Page<SimpleItemToList> getPaginatedItems(Pageable pageable);

	TaskResponse saveItem(SaveItemRequest request);

	TaskResponse updateItem(UUID uuid, UpdateItemRequest request);

	SimpleItemToShow getItemToShow(UUID uuid);

}
