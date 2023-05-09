package com.project.shopmobile.controller;

import com.project.shopmobile.dto.DetailItemRequest;
import com.project.shopmobile.service.ImageService;
import com.project.shopmobile.service.ItemsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/admin")
@RestController
public class AdminController {

    private final ItemsService itemsService;

    private final ImageService imagesService;

    public AdminController(ItemsService itemsService, ImageService imagesService) {
        this.itemsService = itemsService;
        this.imagesService = imagesService;
    }

    @PostMapping("/upload-file")
    public ResponseEntity<?> uploadImages(@RequestParam("file") MultipartFile file) {
        Map<String, String> res = new HashMap<>();
        res.put("data", imagesService.uploadImageToFlickr(file));
        return ResponseEntity.ok(res);
    }

    @PostMapping("item")
    public ResponseEntity<?> createItem(@RequestBody DetailItemRequest detailItemRequest) {
        return ResponseEntity.ok(itemsService.createItem(detailItemRequest));
    }

    @DeleteMapping("/item/{id}")
    public void deleteItem(@PathVariable("id") UUID id) {
        itemsService.deleteItem(id);
    }

}
