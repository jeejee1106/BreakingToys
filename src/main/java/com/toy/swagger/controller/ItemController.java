package com.toy.swagger.controller;

import com.toy.swagger.domain.ItemDto;
import com.toy.swagger.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController("/item")
@RequiredArgsConstructor
public class ItemController {

    public final ItemRepository itemRepository;

    @PostConstruct
    public void init() {
        itemRepository.save(new ItemDto("itemA", 10000, 10));
        itemRepository.save(new ItemDto("itemB", 20000, 20));
    }

    @GetMapping("/test")
    public String swaggerTest() {
        return "성공";
    }

    @GetMapping
    public List<ItemDto> getItemsList(Model model) {
        List<ItemDto> items = itemRepository.findAll();
        return items;
    }

    @PostMapping()
    public ItemDto insertItem(ItemDto itemDto) {
        return itemRepository.save(itemDto);
    }
}
