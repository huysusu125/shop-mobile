package com.project.shopmobile.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class ItemsResponse {
    private Integer type;

    private UUID itemId;
    private String title;
    private String description;
    private BigDecimal minPrice;
    private String image;
}