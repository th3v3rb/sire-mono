package com.dantesoft.sireproductservice.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dantesoft.sireproductservice.payload.request.brand.SaveBrandRequest;
import com.dantesoft.sireproductservice.payload.request.brand.UpdateBrandRequest;
import com.dantesoft.sireproductservice.payload.response.TaskResponse;
import com.dantesoft.sireproductservice.payload.response.brand.SimpleBrandToList;
import com.dantesoft.sireproductservice.payload.response.brand.SimpleBrandToShow;
import com.dantesoft.sireproductservice.services.brand.BrandService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BrandsController {

	final BrandService brandService;

	@GetMapping("/v1/brands/get-all")
	ResponseEntity<Page<SimpleBrandToList>> getPaginatedBrands(
			@PageableDefault(page = 0, size = 10, direction = Direction.ASC, sort = "name") Pageable data) {

		return ResponseEntity.ok(this.brandService.getBrandPage(data));
	}

	@PostMapping("/v1/brands/save")
	ResponseEntity<TaskResponse> saveOneBrand(@Valid @RequestBody SaveBrandRequest entity) {

		return ResponseEntity.ok(this.brandService.saveBrand(entity));
	}

	@PutMapping("/v1/brands/update/{uuid}")
	ResponseEntity<TaskResponse> updateBrand(@PathVariable(name = "uuid", required = true) UUID uuid,
			@RequestBody UpdateBrandRequest entity) {

		return ResponseEntity.ok(this.brandService.updateBrand(uuid, entity));
	}

	@GetMapping("/v1/brands/show/{uuid}")
	ResponseEntity<SimpleBrandToShow> showBrand(@PathVariable UUID uuid) {

		return ResponseEntity.ok(this.brandService.showBrand(uuid));
	}
}
