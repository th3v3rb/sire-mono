package com.dantesoft.sireproductservice.repository;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dantesoft.sireproductservice.entity.Item;
import com.dantesoft.sireproductservice.payload.response.item.SimpleItemToList;
import com.dantesoft.sireproductservice.payload.response.item.SimpleItemToShow;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

	Page<SimpleItemToList> findAllBy(Pageable pageable);

	SimpleItemToShow findAllByUuid(UUID uuid);
}
