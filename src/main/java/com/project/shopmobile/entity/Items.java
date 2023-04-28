package com.project.shopmobile.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@EqualsAndHashCode()
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Table("items")
public class Items {
    @Column("type")
    private Integer type;
    @Column("title")
    private String title;
    @Column("description")
    private String description;
    @Column("minPrice")
    private BigDecimal minPrice;
    @Column("image")
    private String image;
}
