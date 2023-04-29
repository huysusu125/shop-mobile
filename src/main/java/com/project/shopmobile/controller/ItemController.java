package com.project.shopmobile.controller;

import com.project.shopmobile.service.ItemsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/items")
@RestController
public class ItemController {
    private final ItemsService itemsService;

    public ItemController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @GetMapping
    ResponseEntity<?> getAllItems(@RequestParam(required = false) Integer type,
                                  @RequestParam(required = false) String search,
                                  @RequestParam(required = false, defaultValue = "0") Integer page,
                                  @RequestParam(required = false, defaultValue = "1000") Integer size) {
        return itemsService.getAllItems(type, search, page, size);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getDetailItems(@PathVariable UUID id) {
        return itemsService.getDetailItems(id);
    }
}
