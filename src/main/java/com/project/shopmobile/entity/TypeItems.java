package com.project.shopmobile.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@EqualsAndHashCode()
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("type_items")
public class TypeItems {
    @Id
    @Column("id")
    private Integer id;

    @Column("name")
    private String name;
}
