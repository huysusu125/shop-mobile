package com.project.shopmobile.controller;

import com.project.shopmobile.repository.ItemDescriptionRepository;
import com.project.shopmobile.repository.ItemsRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakeDataController {
    private final ItemDescriptionRepository itemDescriptionRepository;
    private final ItemsRepository itemsRepository;
    public FakeDataController(ItemDescriptionRepository itemDescriptionRepository, ItemsRepository itemsRepository) {
        this.itemDescriptionRepository = itemDescriptionRepository;
        this.itemsRepository = itemsRepository;
    }

//    @GetMapping
//    void fakeData() {
//        List<Items> list =  itemsRepository.findAll();
//        list.forEach(item -> itemDescriptionRepository
//                .saveAll(Arrays.asList(
//                        ItemDescription
//                                .builder()
//                                .itemId(item.getId())
//                                .description("Thời gian bảo hành: 12 Tháng chính hãng")
//                                .build(),
//                        ItemDescription
//                                .builder()
//                                .itemId(item.getId())
//                                .description("Thời gian sửa chữa: 15-30 PHÚT.")
//                                .build(),
//                        ItemDescription
//                                .builder()
//                                .itemId(item.getId())
//                                .description("Giá đã có công thợ, không thêm chi phí khác.")
//                                .build()
//                        )));
//    }
}
