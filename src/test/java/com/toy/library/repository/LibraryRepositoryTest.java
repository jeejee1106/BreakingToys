package com.toy.library.repository;

import com.toy.library.entity.Library;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class LibraryRepositoryTest {

    @Autowired
    private LibraryRepository libraryRepository;

    @AfterEach
    void clean() {
        libraryRepository.deleteAll();
    }

    @Test
    void save() {
        //given
        libraryRepository.save(Library.builder()
                .name("test도서관")
                .createDt(LocalDateTime.now())
                .updateDt(LocalDateTime.now())
                .delYn("N")
                .build());

        //when
        List<Library> libraryList = libraryRepository.findAll();

        //then
        Library library = libraryList.get(0);
        Assertions.assertThat(library.getName()).isEqualTo("test도서관");
    }

    @Test
    void findById() {
        //given
        libraryRepository.save(Library.builder()
                .name("서울시립도서관")
                .createDt(LocalDateTime.now())
                .updateDt(LocalDateTime.now())
                .delYn("N")
                .build());

        //when
        Optional<Library> libraryOptional = libraryRepository.findById(1L);
        Library library = libraryOptional.get();

        //then
        Assertions.assertThat(library.getName()).isEqualTo("서울시립도서관");
    }

}
