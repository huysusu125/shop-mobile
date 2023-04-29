package com.project.shopmobile.dto;

import com.project.shopmobile.entity.ItemDescription;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class DetailItem {
    private UUID Id;
    private Integer type;
    private String title;
    private String description;
    private BigDecimal minPrice;
    private String image;
    private List<ItemDescription> descriptionDetails;
}
