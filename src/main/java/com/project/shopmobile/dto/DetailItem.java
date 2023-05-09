package com.project.shopmobile.dto;

import com.project.shopmobile.repository.ItemDescriptionRepository;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class DetailItem implements Serializable {
    private UUID Id;
    private Integer type;
    private String title;
    private String description;
    private BigDecimal minPrice;
    private String image;
    private String timeBaohanh;
    private List<ItemDescriptionRepository.ItemDescriptionInterface> descriptionDetails;
    private List<String> ImageUrls;
}
