package com.toy.swagger.repository;

import com.toy.swagger.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface ItemsRepository extends JpaRepository<Item, Long> {

    @Query("select i from Item i order by i.id desc")
    Stream<Item> findAllDesc();
}
