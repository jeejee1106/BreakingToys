package com.toy.swagger.domain.dto;

import com.toy.swagger.domain.entity.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ItemSaveRequestDto {

    @NotNull
    private String itemName;
    @NotNull
    private Integer price;
    @NotNull
    private Integer quantity;

    public Item toEntity() {
        return Item.builder()
                .itemName(itemName)
                .price(price)
                .quantity(quantity)
                .build();
    }

}
