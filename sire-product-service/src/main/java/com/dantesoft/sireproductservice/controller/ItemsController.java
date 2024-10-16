package com.dantesoft.sireproductservice.controller;

import com.dantesoft.sireproductservice.entity.Item;
import com.dantesoft.sireproductservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemsController {
    final ItemService itemService;

    @GetMapping("/v1/product")
    Page<Item> index(@PageableDefault Pageable pageable) {
        return this.itemService.handleListPaginatedItems(pageable);
    }
}


