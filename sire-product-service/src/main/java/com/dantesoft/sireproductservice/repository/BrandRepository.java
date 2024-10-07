package com.dantesoft.sireproductservice.repository;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dantesoft.sireproductservice.entity.Brand;
import com.dantesoft.sireproductservice.payload.response.brand.SimpleBrandToList;
import com.dantesoft.sireproductservice.payload.response.brand.SimpleBrandToShow;
import com.google.common.base.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID> {

	Page<SimpleBrandToList> findAllBy(Pageable page);

	Optional<SimpleBrandToShow> findByUuid(UUID uuid);
}
