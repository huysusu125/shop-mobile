package com.project.shopmobile.service;

import com.project.shopmobile.dto.DetailItem;
import com.project.shopmobile.dto.ListRes;
import com.project.shopmobile.entity.ItemDescription;
import com.project.shopmobile.entity.Items;
import com.project.shopmobile.repository.ItemDescriptionRepository;
import com.project.shopmobile.repository.ItemsRepository;
import com.project.shopmobile.repository.TypeItemsRepository;
import com.project.shopmobile.specification.ItemsSpecification;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Service
public class ItemsService {
    private final TypeItemsRepository typeItemsRepository;
    private final ItemsRepository itemsRepository;
    private final ItemDescriptionRepository itemDescriptionRepository;
    public ItemsService(TypeItemsRepository typeItemsRepository, ItemsRepository itemsRepository, ItemDescriptionRepository itemDescriptionRepository) {
        this.typeItemsRepository = typeItemsRepository;
        this.itemsRepository = itemsRepository;
        this.itemDescriptionRepository = itemDescriptionRepository;
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

    @SneakyThrows
    public ResponseEntity<?> getDetailItems(UUID id) {
        CompletableFuture<Items> completableFuture = CompletableFuture
                .supplyAsync(() -> itemsRepository.findById(id).get());
        CompletableFuture<List<ItemDescriptionRepository.ItemDescriptionInterface>> completableFuture1 = CompletableFuture
                .supplyAsync(() -> itemDescriptionRepository.findAllByItemId(id));
        return ResponseEntity.ok(DetailItem
                .builder()
                        .Id(completableFuture.get().getId())
                        .type(completableFuture.get().getType())
                        .title(completableFuture.get().getTitle())
                        .description(completableFuture.get().getDescription())
                        .minPrice(completableFuture.get().getMinPrice())
                        .image(completableFuture.get().getImage())
                        .descriptionDetails(completableFuture1.get())
                .build());
    }
}
