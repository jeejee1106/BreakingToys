package com.toy.swagger.repository;

import com.toy.swagger.domain.entity.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ItemsRepositoryTest {

    @Autowired
    ItemsRepository itemsRepository;

    @AfterEach
    public void clean() {
        itemsRepository.deleteAll();
    }

    @Test
    public void getItemSave() {
        //given
        itemsRepository.save(Item.builder()
                .itemName("testItem")
                .price(1000)
                .quantity(10)
                .build());

        //then
        List<Item> itemList = itemsRepository.findAll();

        //when
        Item item = itemList.get(2); //Controller에서 @PostConstruct로 데이터 두 개를 넣어줬기 때문에 얜 2의 인덱스 값을 갖는다.
        Assertions.assertThat(item.getItemName()).isEqualTo("testItem");
    }
    @Test
    public void getItemSaveFail() {
        //given
        itemsRepository.save(Item.builder()
                .itemName("testItem")
                .price(1000)
                .quantity(10)
                .build());

        //then
        List<Item> itemList = itemsRepository.findAll();

        //when
        Item item = itemList.get(0); //clean 메서드로 데이터베이스를 싹 비우기 때문에 0의 인덱스를 갖는다.
        Assertions.assertThat(item.getItemName()).isNotEqualTo("testItem1");
        //의문 : 실패 테스트는 이렇게 따로 또 만들어야하나?
        //위 코드한 줄만 성공 테스트에 같이 넣으면 안되나???
    }
}
