//package com.project.shopmobile.controller;
//
//import com.project.shopmobile.service.ImageService;
//import com.project.shopmobile.service.ItemsService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//@RequestMapping("/admin")
//@RestController
//public class AdminController {
//
//    private final ItemsService itemsService;
//
//    private final ImageService imagesService;
//
//    public AdminController(ItemsService itemsService, ImageService imagesService) {
//        this.itemsService = itemsService;
//        this.imagesService = imagesService;
//    }
//
//    @PostMapping()
//    public ResponseEntity<?> uploadImages(@RequestParam("file") MultipartFile file) {
//        return ResponseEntity.ok(imagesService.uploadImageToFlickr(file));
//    }
//
//}
