package com.project.shopmobile.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "type_items")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeItems implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "banner")
    private String banner;
}
