package com.project.shopmobile.repository;

import com.project.shopmobile.entity.ItemDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface ItemDescriptionRepository extends JpaRepository<ItemDescription, Long> {

//    List<ItemDescription> findAllByItemId(UUID itemId);
    List<ItemDescriptionInterface> findAllByItemId(UUID itemId);

    @Modifying
    @Query("delete from ItemDescription where itemId = :id")
    @Transactional
    void deleteByItemId(UUID id);

    interface ItemDescriptionInterface {
        Long getId();
        String getDescription();
    }
}
