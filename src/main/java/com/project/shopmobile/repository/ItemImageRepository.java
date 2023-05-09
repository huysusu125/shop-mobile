package com.project.shopmobile.repository;


import com.project.shopmobile.entity.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface ItemImageRepository extends JpaRepository<ItemImage, UUID> {

    List<ItemImageInterface> findAllByItemId(UUID itemId);

    @Modifying
    @Query("delete from ItemImage where itemId = :id")
    @Transactional
    void deleteByItemId(UUID id);

    interface ItemImageInterface {
        Long getId();
        String getImage();
    }
}
