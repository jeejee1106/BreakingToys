package com.toy.swagger.domain.dto;

import com.toy.swagger.domain.entity.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
public class ItemFindAllDescResponseDto {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public ItemFindAllDescResponseDto(Item entity) {
        id = entity.getId();
        itemName = entity.getItemName();
        price = entity.getPrice();
        quantity = entity.getQuantity();
    }

}
