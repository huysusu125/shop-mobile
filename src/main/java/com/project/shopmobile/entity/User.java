package com.project.shopmobile.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "\"user\"")
@Entity
public class User {

    @Id
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}
