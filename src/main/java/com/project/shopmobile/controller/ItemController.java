package com.project.shopmobile.controller;

import com.project.shopmobile.dto.ListRes;
import com.project.shopmobile.service.ItemsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("/items")
@RestController
public class ItemController {
    private final ItemsService itemsService;

    public ItemController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @GetMapping
    Mono<?> getAllItems(@RequestParam Integer type,
                              @RequestParam(required = false) String search,
                              @RequestParam(required = false, defaultValue = "0") Integer page,
                              @RequestParam(required = false, defaultValue = "1000") Integer size) {
        return itemsService.getAllItems(type, search, page, size);
    }
}
