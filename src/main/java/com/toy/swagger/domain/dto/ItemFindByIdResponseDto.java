package com.toy.swagger.domain.dto;

import com.toy.swagger.domain.entity.Item;
import lombok.Getter;

@Getter
public class ItemFindByIdResponseDto {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public ItemFindByIdResponseDto(Item entity) {
        id = entity.getId();
        itemName = entity.getItemName();
        price = entity.getPrice();
        quantity = entity.getQuantity();
    }

}
