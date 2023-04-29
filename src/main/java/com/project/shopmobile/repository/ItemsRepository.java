package com.project.shopmobile.repository;

import com.project.shopmobile.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ItemsRepository extends JpaRepository<Items, UUID>, JpaSpecificationExecutor<Items> {
}
