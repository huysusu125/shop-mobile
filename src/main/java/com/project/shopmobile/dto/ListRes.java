package com.project.shopmobile.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ListRes {
    private Object data;
    private Long count;
}
