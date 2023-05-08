package com.project.shopmobile.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Builder
public class ListRes implements Serializable {
    private Object data;
    private Long count;
}
