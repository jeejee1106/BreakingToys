package com.toy.library.service;

import com.toy.library.dto.BookReqDto;
import com.toy.library.dto.BookResDto;
import com.toy.library.entity.Book;
import com.toy.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookResDto.BookRes saveBook(BookReqDto.SaveBookReq bookReqDto) {
        Book entity = bookRepository.save(bookReqDto.toEntity());
        return new BookResDto.BookRes(entity);
    }

    public BookResDto.SelectBookListRes findAll() {
        List<Book> list = bookRepository.findAll();
        return new BookResDto.SelectBookListRes(list, list.size());
    }

    public BookResDto.BookRes findById(Long bookNo) {
        Book entity = bookRepository.findById(bookNo).orElseThrow(() -> new IllegalArgumentException(bookNo + "번에 해당하는 도서가 없습니다."));
        return new BookResDto.BookRes(entity);
    }

    public void deleteByIdPhysical(Long bookNo) {
        bookRepository.deleteByIdPhysical(bookNo);
    }

    public void deleteByIdLogical(Long bookNo) {
        bookRepository.deleteByIdLogical(bookNo);
    }

}
