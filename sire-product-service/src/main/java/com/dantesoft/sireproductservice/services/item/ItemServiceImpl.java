package com.dantesoft.sireproductservice.services.item;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dantesoft.sireproductservice.payload.request.item.SaveItemRequest;
import com.dantesoft.sireproductservice.payload.request.item.UpdateItemRequest;
import com.dantesoft.sireproductservice.payload.response.TaskResponse;
import com.dantesoft.sireproductservice.payload.response.item.SimpleItemToList;
import com.dantesoft.sireproductservice.payload.response.item.SimpleItemToShow;
import com.dantesoft.sireproductservice.repository.BrandRepository;
import com.dantesoft.sireproductservice.repository.CategoryRepository;
import com.dantesoft.sireproductservice.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

	final ItemRepository itemRepository;
	final BrandRepository brandRepo;
	final CategoryRepository categoryRepo;

	@Override
	public Page<SimpleItemToList> getPaginatedItems(Pageable pageable) {
		return this.itemRepository
				.findAllBy(pageable);
	}

	@Override
	public TaskResponse saveItem(SaveItemRequest request) {

		// var searchBrandResult =
		// this.brandRepo.findById(request.getBrandUuid())
		// .orElseThrow(() -> new NotFoundException("Brand doesnt exist"));

		// var searchCategoriesResult =
		// this.categoryRepo.findAllById(request.getCategoriesIds());

		// if (searchCategoriesResult.isEmpty())
		// throw new NotFoundException("Not found categories");

		// var categoriesFormmatted =
		// searchCategoriesResult.stream().map(category ->
		// category)
		// .collect(Collectors.toSet());

		// this.itemRepository.save(newItem);

		// return TaskResponse.builder()
		// .message("Item stored")
		// .description("Task successfully ended")
		// .build();
		return null;
	}

	@Override
	public TaskResponse updateItem(UUID uuid, UpdateItemRequest request) {

		// var searchItemResult =
		// this.itemRepository.findById(uuid).orElseThrow(() ->
		// new NotFoundException("Item doesnt found"));

		// var searchBrandResult =
		// this.brandRepo.findById(request.getBrandUuid())
		// .orElseThrow(() -> new NotFoundException("Brand doesnt exist"));

		// var searchCategoriesResult =
		// this.categoryRepo.findAllById(request.getCategoriesIds());

		// if (searchCategoriesResult.isEmpty())
		// throw new NotFoundException("Not found categories");

		// var categoriesFormmatted =
		// searchCategoriesResult.stream().map(category ->
		// category)
		// .collect(Collectors.toSet());

		// searchItemResult.setName(request.getName());
		// searchItemResult.setDescription(request.getDescription());
		// searchItemResult.setImageUrl(request.getImageUrl());
		// searchItemResult.setBarCode(request.getBarCode());
		// searchItemResult.setWarehouseLocation(request.getWarehouseLocation());
		// searchItemResult.setUnitMeasure(request.getUnitMeasure());
		// searchItemResult.setQuantity(request.getQuantiy());
		// searchItemResult.setBuyPrice(request.getBuyPrice());
		// searchItemResult.setS

		// this.itemRepository.save(newItem);

		// return TaskResponse.builder()
		// .message("Item updated")
		// .description("Task successfully ended")
		// .build();
		return null;
	}

	@Override
	public SimpleItemToShow getItemToShow(UUID uuid) {
		return null;
	}
}
