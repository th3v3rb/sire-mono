package com.dantesoft.sireproductservice.services.category;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.dantesoft.sireproductservice.entity.Category;
import com.dantesoft.sireproductservice.payload.request.category.SaveCategoryRequest;
import com.dantesoft.sireproductservice.payload.request.category.UpdateCategoryRequest;
import com.dantesoft.sireproductservice.payload.response.TaskResponse;
import com.dantesoft.sireproductservice.payload.response.category.SimpleCategoryToList;
import com.dantesoft.sireproductservice.payload.response.category.SimpleCategoryToShow;
import com.dantesoft.sireproductservice.repository.CategoryRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	final CategoryRepository categoryRepo;

	@Override
	public Page<SimpleCategoryToList> getCategoryPage(Pageable pageable) {
		return this.categoryRepo
				.getAllBy(pageable);
	}

	@Override
	public TaskResponse saveCategory(SaveCategoryRequest request) {
		var categoryToSave = Category
				.builder()
				.name(request
						.getName())
				.active(true)
				.build();
		this.categoryRepo
				.save(categoryToSave);

		return TaskResponse
				.builder()
				.message("Category created")
				.description("Task successfully ended")
				.build();
	}

	@Override
	public TaskResponse updateCategory(UUID uuid,
			UpdateCategoryRequest request) {
		var databaseCategory = this.categoryRepo
				.findById(uuid)
				.orElseThrow(NotFoundException::new);

		databaseCategory
				.setName(request
						.getName());
		databaseCategory
				.setActive(request
						.isActive());

		this.categoryRepo
				.save(databaseCategory);

		return TaskResponse
				.builder()
				.message("Category updated")
				.description("Task successfully ended")
				.build();
	}

	@Override
	public SimpleCategoryToShow showCategory(UUID uuid) {
		var databaseCategory = this.categoryRepo
				.findByUuid(uuid);

		if (databaseCategory
				.isEmpty())
			throw new NotFoundException("Not found element on database");

		return databaseCategory
				.get();
	}
}
