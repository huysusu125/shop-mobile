package com.project.shopmobile.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("/items")
@RestController
public class ItemController {
    @GetMapping
    Mono<String> test() {
        return Mono.just("hello");
    }
}
