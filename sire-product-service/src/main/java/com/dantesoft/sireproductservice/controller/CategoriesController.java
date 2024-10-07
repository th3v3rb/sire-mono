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
import com.dantesoft.sireproductservice.payload.request.category.SaveCategoryRequest;
import com.dantesoft.sireproductservice.payload.request.category.UpdateCategoryRequest;
import com.dantesoft.sireproductservice.payload.response.TaskResponse;
import com.dantesoft.sireproductservice.payload.response.category.SimpleCategoryToList;
import com.dantesoft.sireproductservice.payload.response.category.SimpleCategoryToShow;
import com.dantesoft.sireproductservice.services.category.CategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoriesController {

	final CategoryService categoryService;

	@GetMapping("/v1/categories/get-all")
	public ResponseEntity<Page<SimpleCategoryToList>> getPaginatedCategories(
			@PageableDefault(page = 0, size = 10, sort = "name", direction = Direction.ASC) Pageable pageable) {

		return ResponseEntity.ok(this.categoryService.getCategoryPage(pageable));
	}

	@PostMapping("/v1/categories/save")
	public ResponseEntity<TaskResponse> saveCategory(
			@RequestBody SaveCategoryRequest request) {

		return ResponseEntity.ok(this.categoryService.saveCategory(request));
	}

	@PutMapping("/v1/categories/update/{uuid}")
	public ResponseEntity<TaskResponse> updateCategory(
			@PathVariable(name = "uuid") UUID uuid,
			@RequestBody UpdateCategoryRequest request) {

		return ResponseEntity.ok(this.categoryService.updateCategory(uuid, request));
	}

	@GetMapping("/v1/categories/show/{uuid}")
	public ResponseEntity<SimpleCategoryToShow> showCategory(
			@PathVariable(name = "uuid") UUID uuid) {
		return ResponseEntity.ok(this.categoryService.showCategory(uuid));
	}
}
