package com.dantesoft.sireproductsservice.controller;

import com.dantesoft.sireproductsservice.entity.Category;
import com.dantesoft.sireproductsservice.service.CategoryService;
import com.dantesoft.sireproductsservice.validation.ValidCategoryRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {
    final CategoryService categoryService;

    @GetMapping("/v1/product/categories")
    Page<Category> index(@PageableDefault Pageable pageable) {
        return this.categoryService.handleGetPaginatedCategories(pageable);
    }


    @PostMapping("/v1/product/category")
    ResponseEntity<Object> store(ValidCategoryRegister request) {
        this.categoryService.handleStoreCategory(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/v1/product/category/{id}")
    ResponseEntity<Object> update(ValidCategoryRegister request, @PathVariable UUID id) {
        this.categoryService.handleUpdateCategory(request, id);
        return ResponseEntity.ok().build();
    }
}
