package com.dantesoft.sireproductservice.services.brand;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.dantesoft.sireproductservice.entity.Brand;
import com.dantesoft.sireproductservice.payload.request.brand.SaveBrandRequest;
import com.dantesoft.sireproductservice.payload.request.brand.UpdateBrandRequest;
import com.dantesoft.sireproductservice.payload.response.TaskResponse;
import com.dantesoft.sireproductservice.payload.response.brand.SimpleBrandToList;
import com.dantesoft.sireproductservice.payload.response.brand.SimpleBrandToShow;
import com.dantesoft.sireproductservice.repository.BrandRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

	final BrandRepository brandRepo;

	@Override
	public Page<SimpleBrandToList> getBrandPage(Pageable pageable) {
		return this.brandRepo.findAllBy(pageable);
	}

	@Override
	public TaskResponse saveBrand(SaveBrandRequest request) {

		var saveData = Brand.builder().name(request.getName()).description(
			request.getDescription()).active(true).build();
		this.brandRepo.save(saveData);

		return TaskResponse.builder().message("Brand created").description("Task successfully ended").build();
	}

	@Override
	public TaskResponse updateBrand(UUID uuid, UpdateBrandRequest request) {

		var databaseBrand = this.brandRepo.findById(uuid).orElseThrow(
			() -> new NotFoundException("the element doesnt exist on db"));

		databaseBrand.setName(request.getName());
		databaseBrand.setDescription(request.getDescription());
		databaseBrand.setActive(request.isActive());

		this.brandRepo.save(databaseBrand);

		return TaskResponse.builder().message("Brand updated").description("Task successfully ended").build();
	}

	@Override
	public SimpleBrandToShow showBrand(UUID uuid) {
		var findBrand = this.brandRepo.findByUuid(uuid);

		if (!findBrand.isPresent()) throw new NotFoundException("Element not found");

		return findBrand.get();
	}
}
