package com.project.shopmobile.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class DetailItemRequest {

    private Integer type;
    private String title;
    private String description;
    private BigDecimal minPrice;
    private String image;
    private String timeBaohanh;
    private List<String> descriptionDetails;
    private List<String> imageUrls;
}
