package com.project.shopmobile.controller;

import com.project.shopmobile.service.ItemsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RequestMapping("/type-items")
@RestController
public class TypeItemsController {

    private final ItemsService itemsService;

    public TypeItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }
    @GetMapping
    Flux<?> getAllTypeItems() {
        return itemsService.getAllTypeItems();
    }
}
