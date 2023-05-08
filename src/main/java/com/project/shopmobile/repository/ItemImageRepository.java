package com.project.shopmobile.repository;


import com.project.shopmobile.entity.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemImageRepository extends JpaRepository<ItemImage, UUID> {

    List<ItemImageInterface> findAllByItemId(UUID itemId);
    interface ItemImageInterface {
        Long getId();
        String getImage();
    }
}
