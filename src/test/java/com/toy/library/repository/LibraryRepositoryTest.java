package com.toy.library.repository;

import com.toy.library.entity.Library;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class LibraryRepositoryTest {

    @Autowired
    private LibraryRepository libraryRepository;

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

}
