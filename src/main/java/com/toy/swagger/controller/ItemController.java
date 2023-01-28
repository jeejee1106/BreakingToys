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

    /* 스웨거 주소 : http://localhost:8080/swagger-ui/index.html */
    /* h2 주소 : http://localhost:8080/h2-console */

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
    public String saveItem(ItemSaveRequestDto dto) {
        itemService.saveItem(dto);
        return "아이템 저장 완료";
    }

    @GetMapping()
    public List<Item> findAll() {
        List<Item> items = itemService.findAll();
        return items;
    }

    @GetMapping("/desc")
    public List<ItemFindAllDescResponseDto> findAllDesc() {
        List<ItemFindAllDescResponseDto> items = itemService.findAllDesc();
        return items;
    }

    @GetMapping("/{id}")
    public Optional<Item> findById(@PathVariable Long id) {
        Optional<Item> items = itemService.findById(id);
        return items;
    }

    @DeleteMapping
    public String deleteAll() {
        itemService.deleteAll();
        return "모든 상품이 삭제되었습니다.";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        itemService.deleteById(id);
        return id + "번 상품이 삭제되었습니다.";
    }
}
