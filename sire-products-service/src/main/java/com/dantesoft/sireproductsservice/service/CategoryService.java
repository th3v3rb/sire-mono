package com.dantesoft.sireproductsservice.service;

import com.dantesoft.sireproductsservice.entity.Category;
import com.dantesoft.sireproductsservice.exceptions.CategoryNotFoundException;
import com.dantesoft.sireproductsservice.validation.ValidCategoryRegister;
import com.dantesoft.sireproductsservice.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryRepository categoryRepo;


    public Page<Category> handleGetPaginatedCategories(Pageable pageable) {
        return this.categoryRepo.findAll(pageable);
    }

    @Transactional
    public void handleStoreCategory(ValidCategoryRegister data) {
        var category = new Category();
        category.setName(data.getName());
        categoryRepo.save(category);
    }

    @Transactional
    public void handleUpdateCategory(ValidCategoryRegister data, UUID id) {
        var oldCategory = categoryRepo.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        oldCategory.setName(data.getName());

        categoryRepo.save(oldCategory);
    }

    @Transactional
    public Category handleGetCategoryById(UUID id) {
        return this.categoryRepo.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }

}
