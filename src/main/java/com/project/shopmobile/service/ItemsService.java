package com.project.shopmobile.service;

import com.cloudinary.utils.ObjectUtils;
import com.project.shopmobile.dto.DetailItem;
import com.project.shopmobile.dto.DetailItemRequest;
import com.project.shopmobile.dto.ListRes;
import com.project.shopmobile.dto.LoginRequest;
import com.project.shopmobile.entity.ItemDescription;
import com.project.shopmobile.entity.ItemImage;
import com.project.shopmobile.entity.Items;
import com.project.shopmobile.entity.User;
import com.project.shopmobile.repository.*;
import com.project.shopmobile.specification.ItemsSpecification;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Service
public class ItemsService {
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;
    private final TypeItemsRepository typeItemsRepository;
    private final ItemsRepository itemsRepository;
    private final ItemDescriptionRepository itemDescriptionRepository;

    private final ItemImageRepository itemImageRepository;

    public ItemsService(JwtTokenService jwtTokenService, UserRepository userRepository,
                        TypeItemsRepository typeItemsRepository,
                        ItemsRepository itemsRepository,
                        ItemDescriptionRepository itemDescriptionRepository, ItemImageRepository itemImageRepository) {
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
        this.typeItemsRepository = typeItemsRepository;
        this.itemsRepository = itemsRepository;
        this.itemDescriptionRepository = itemDescriptionRepository;
        this.itemImageRepository = itemImageRepository;
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
        CompletableFuture<List<ItemImageRepository.ItemImageInterface>> completableFuture3 = CompletableFuture
                .supplyAsync(() -> itemImageRepository.findAllByItemId(id));
        CompletableFuture<Items> completableFuture = CompletableFuture
                .supplyAsync(() -> itemsRepository.findById(id).orElse(null));
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
                .timeBaohanh(completableFuture.get().getTimeBaohanh())
                .ImageUrls(completableFuture3
                        .get()
                        .stream()
                        .map(ItemImageRepository.ItemImageInterface::getImage)
                        .collect(Collectors.toList()))
                .build());
    }

    public ResponseEntity<?> login(LoginRequest loginRequest) {
        User user = userRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tên đăng nhập hoặc mật khẩu không đúng");
        }
        Map<String, String> response = new HashMap<>();
        response.put("accessToken", jwtTokenService.generateOauth2AccessToken(user));
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getRandomAllItems() {
        return ResponseEntity.ok(itemsRepository.findRandom());
    }

    @Transactional
    public Object createItem(DetailItemRequest detailItemRequest) {
        Items items = itemsRepository.save(Items
                .builder()
                .image(detailItemRequest.getImage())
                .type(detailItemRequest.getType())
                .title(detailItemRequest.getTitle())
                .description(detailItemRequest.getDescription())
                .timeBaohanh(detailItemRequest.getTimeBaohanh())
                        .Id(UUID.randomUUID())
                .build());

        itemDescriptionRepository.saveAll(detailItemRequest
                .getDescriptionDetails()
                .stream()
                .map(description -> ItemDescription
                        .builder()
                        .itemId(items.getId())
                        .description(description)
                        .build())
                .collect(Collectors.toList()));

        itemImageRepository.saveAll(detailItemRequest
                .getImageUrls()
                .stream()
                .map(imageUrl -> ItemImage
                        .builder()
                        .itemId(items.getId())
                        .image(imageUrl)
                        .build())
                .collect(Collectors.toList()));
        return ObjectUtils.asMap("id", items.getId());
    }


    public void deleteItem(UUID id) {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> itemsRepository.deleteById(id));
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> itemDescriptionRepository.deleteByItemId(id));
        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> itemImageRepository.deleteByItemId(id));

    }
}
