package com.dantesoft.sireproductservice.services.category;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.dantesoft.sireproductservice.payload.request.category.SaveCategoryRequest;
import com.dantesoft.sireproductservice.payload.request.category.UpdateCategoryRequest;
import com.dantesoft.sireproductservice.payload.response.TaskResponse;
import com.dantesoft.sireproductservice.payload.response.category.SimpleCategoryToList;
import com.dantesoft.sireproductservice.payload.response.category.SimpleCategoryToShow;

public interface CategoryService {

	Page<SimpleCategoryToList> getCategoryPage(Pageable pageable);

	TaskResponse saveCategory(SaveCategoryRequest request);

	TaskResponse updateCategory(UUID uuid, UpdateCategoryRequest request);

	SimpleCategoryToShow showCategory(UUID uuid);
}
