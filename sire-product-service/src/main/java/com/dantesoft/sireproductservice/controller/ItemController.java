package com.dantesoft.sireproductservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dantesoft.sireproductservice.services.item.ItemService;
import lombok.RequiredArgsConstructor;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemController {

	final ItemService itemService;

	@GetMapping("/v1/items/get-all")
	public String getMethodName(@RequestParam String param) {
		return new String();
	}

	@PostMapping("/v1/items/save")
	public String postMethodName(@RequestBody String entity) {
		// TODO: process POST request

		return entity;
	}

	@PutMapping("/v1/items/update/{uuid}")
	public String putMethodName(@PathVariable String id,
			@RequestBody String entity) {
		// TODO: process PUT request

		return entity;
	}

	@GetMapping("/v1/items/show/{uuid}")
	public String getMethodName(@PathVariable UUID uuid,
			@RequestBody String entity) {
		return new String();
	}

}
