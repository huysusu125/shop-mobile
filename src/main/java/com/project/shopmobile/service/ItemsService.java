package com.project.shopmobile.service;

import com.project.shopmobile.dto.ListRes;
import com.project.shopmobile.entity.Items;
import com.project.shopmobile.repository.ItemsRepository;
import com.project.shopmobile.repository.TypeItemsRepository;
import com.project.shopmobile.specification.ItemsSpecification;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ItemsService {
    private final TypeItemsRepository typeItemsRepository;
    private final ItemsRepository itemsRepository;
    public ItemsService(TypeItemsRepository typeItemsRepository, ItemsRepository itemsRepository) {
        this.typeItemsRepository = typeItemsRepository;
        this.itemsRepository = itemsRepository;
    }

    @SneakyThrows
    public ResponseEntity<?> getAllItems(Integer type, String search, Integer page, Integer size) {
        Page<Items> itemsPage = itemsRepository
                .findAll(ItemsSpecification.filterItems(search, type),
                        PageRequest.of(page, size));
        return ResponseEntity
                .ok(ListRes
                        .builder()
                        .data(itemsPage.getContent())
                        .count(itemsPage.getTotalElements())
                        .build());

    }

    public ResponseEntity<?> getAllTypeItems() {
        return ResponseEntity.ok(typeItemsRepository.findAll());
    }
}
