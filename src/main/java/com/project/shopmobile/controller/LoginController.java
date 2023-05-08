package com.project.shopmobile.controller;

import com.project.shopmobile.dto.LoginRequest;
import com.project.shopmobile.service.ItemsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final ItemsService itemsService;

    public LoginController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return itemsService.login(loginRequest);
    }
}
