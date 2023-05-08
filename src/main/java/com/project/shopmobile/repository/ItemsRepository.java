package com.project.shopmobile.repository;

import com.project.shopmobile.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ItemsRepository extends JpaRepository<Items, UUID>, JpaSpecificationExecutor<Items> {
    @Query(value = "SELECT * FROM items ORDER BY RANDOM() LIMIT 6", nativeQuery = true)
    List<Items> findRandom();

    @Query(value = "SELECT * FROM items ORDER BY RANDOM() LIMIT 6", nativeQuery = true)
    List<Items> findRandomByType(Integer type);
}
