package com.toy.swagger.controller;

import com.toy.swagger.domain.dto.ItemFindAllDescResponseDto;
import com.toy.swagger.domain.dto.ItemSaveRequestDto;
import com.toy.swagger.domain.entity.Item;
import com.toy.swagger.repository.ItemsRepository;
import com.toy.swagger.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    public final ItemsRepository itemsRepository;
    public final ItemService itemService;

    @PostConstruct
    public void init() {
        itemsRepository.save(Item.builder()
                .itemName("test1")
                .price(1000)
                .quantity(10)
                .build());
        itemsRepository.save(Item.builder()
                .itemName("test2")
                .price(2000)
                .quantity(300)
                .build());
    }

    @PostMapping
    public void saveItem(ItemSaveRequestDto dto) {
        itemsRepository.save(dto.toEntity());
    }

    @GetMapping("/{id}")
    public Optional<Item> findById(@PathVariable Long id) {
        Optional<Item> items = itemService.findById(id);
        return items;
    }

    @GetMapping
    public List<ItemFindAllDescResponseDto> findAllDesc() {
        List<ItemFindAllDescResponseDto> items = itemService.findAllDesc();
        return items;
    }
}
