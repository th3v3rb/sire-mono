package com.dantesoft.sireproductservice.service;

import com.dantesoft.sireproductservice.entity.Item;
import com.dantesoft.sireproductservice.exceptions.ItemNotFoundException;
import com.dantesoft.sireproductservice.validation.ValidItemRegister;
import com.datesoft.gula.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {
    final ItemRepository itemRepo;
    final CategoryService categoryService;

    public Page<Item> handleListPaginatedItems(Pageable pageable) {
        return this.itemRepo.findAll(pageable);
    }

    @Transactional
    public void handleStoreItems(ValidItemRegister data) {
        var item = new Item();
        var category = this.categoryService.handleGetCategoryById(data.getCategory());
        item.setCategory(category);
        item.setName(data.getName());
        item.setDescription(data.getDescription());
        item.setPrice(data.getPrice());

        this.itemRepo.save(item);
    }

    @Transactional
    public void handleUpdateItem(ValidItemRegister data, UUID id) {
        var existingItem = this.itemRepo
                .findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item with ID " + id + " not found"));

        var category = this.categoryService.handleGetCategoryById(data.getCategory());

        existingItem.setCategory(category);
        existingItem.setName(data.getName());
        existingItem.setDescription(data.getDescription());
        existingItem.setPrice(data.getPrice());

        this.itemRepo.save(existingItem);
    }


}
