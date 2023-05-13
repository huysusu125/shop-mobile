package com.project.shopmobile.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "items")
@Entity
@Getter
@Setter
public class Items implements Serializable {

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
    @JsonIgnore
    @Column(name = "created_at")
    private Long createdAt;
}
