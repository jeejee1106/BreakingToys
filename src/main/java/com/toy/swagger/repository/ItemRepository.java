package com.toy.swagger.repository;

import com.toy.swagger.domain.ItemDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, ItemDto> store = new HashMap<>();
    private static Long sequence = 0L;

    public List<ItemDto> findAll() {
        return new ArrayList<>(store.values());
    }

    public ItemDto save(ItemDto itemDto) {
        itemDto.setId(++sequence);
        store.put(itemDto.getId(), itemDto);
        return itemDto;
    }
}
