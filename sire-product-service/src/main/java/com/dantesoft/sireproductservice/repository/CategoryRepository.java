package com.dantesoft.sireproductservice.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dantesoft.sireproductservice.entity.Category;
import com.dantesoft.sireproductservice.payload.response.category.SimpleCategoryToList;
import com.dantesoft.sireproductservice.payload.response.category.SimpleCategoryToShow;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

	Page<SimpleCategoryToList> getAllBy(Pageable page);

	Optional<SimpleCategoryToShow> findByUuid(UUID uuid);

}
