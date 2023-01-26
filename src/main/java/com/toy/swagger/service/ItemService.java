package com.toy.swagger.service;

import com.toy.swagger.domain.dto.ItemFindAllDescResponseDto;
import com.toy.swagger.domain.entity.Item;
import com.toy.swagger.repository.ItemsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemService {

    private ItemsRepository itemsRepository;

    @Transactional(readOnly = true) //readOnly = true 옵션은 트랜잭션 범위는 유지하되, 조회 기능만 남겨두는 역할을 한다. 조회 속도 개선!
    public Optional<Item> findById(Long id) {
        return itemsRepository.findById(id);
    }

    @Transactional(readOnly = true) //readOnly = true 옵션은 트랜잭션 범위는 유지하되, 조회 기능만 남겨두는 역할을 한다. 조회 속도 개선!
    public List<ItemFindAllDescResponseDto> findAllDesc() {
        return itemsRepository.findAllDesc()
                //repository 결과로 넘어온 Item의 Stream을 map을 통해 ItemFindAllDescResponseDto 변환 -> List로 반환하는 메서드
                .map(ItemFindAllDescResponseDto::new)
                .collect(Collectors.toList());
    }

}
