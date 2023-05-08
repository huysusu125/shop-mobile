package com.project.shopmobile.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "items")
@Entity
@Getter
@Setter
public class Items {

    @Id
    private UUID Id;
    @Column(name = "type")
    private Integer type;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "min_price")
    private BigDecimal minPrice;
    @Column(name = "image")
    private String image;
    @Column(name = "time_baohanh")
    private String timeBaohanh;
}
