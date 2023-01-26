package com.toy.swagger.domain.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ItemFindByIdRequestDto {

    @NotNull
    private Long id;

}
