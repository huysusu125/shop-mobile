package com.project.shopmobile.repository;

import com.project.shopmobile.entity.ItemDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemDescriptionRepository extends JpaRepository<ItemDescription, Long> {

    List<ItemDescription> findAllByItemId(UUID itemId);
}
