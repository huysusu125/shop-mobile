package com.project.shopmobile.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "\"user\"")
public class User extends BaseEntity {
    @Column("username")
    private String username;
    @Column("password")
    private String password;
}
