package com.project.shopmobile.controller;

import com.project.shopmobile.service.ItemsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/type-items")
@RestController
public class TypeItemsController {

    private final ItemsService itemsService;

    public TypeItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }
    @GetMapping
    ResponseEntity<?> getAllTypeItems() {
        return itemsService.getAllTypeItems();
    }
}
