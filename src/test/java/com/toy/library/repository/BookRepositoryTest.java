package com.toy.library.repository;

import com.toy.library.entity.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@SpringBootTest
@Rollback
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    @DisplayName("도서 삭제 테스트")
    public void deleteBookTest() {
        //given
        Book book1 = Book.builder()
                .title("test")
                .delYn("N")
                .loanStatusYn("N")
                .lossYn("N")
                .rsvStatusYn("N")
                .build();

        Book book2 = Book.builder()
                .title("test2")
                .delYn("N")
                .loanStatusYn("N")
                .lossYn("N")
                .rsvStatusYn("N")
                .build();
        bookRepository.save(book1);
        bookRepository.save(book2);

        //when
        bookRepository.deleteById(book1);
        Long deleteCount = bookRepository.count();

        //then
        //테스트는 실제 DB에 있는 값으로도 테스트 하는구낭..! test용 DB(H2)를 설정해야하나?
        Assertions.assertThat(deleteCount).isEqualTo(6L); //test용 DB설정하면 기대값이 1L이 되게 설정하기
    }

}