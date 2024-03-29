package com.toy.library.service;

import com.toy.library.dto.BookReqDto;
import com.toy.library.dto.BookResDto;
import com.toy.library.entity.Book;
import com.toy.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public BookResDto.BookRes saveBook(BookReqDto.SaveBookReq req) {
        Book book = bookRepository.save(req.toEntity());
        return new BookResDto.BookRes(book);
    }

    public BookResDto.SelectBookListRes findAll() {
        List<Book> list = bookRepository.findAll();
        return new BookResDto.SelectBookListRes(list, list.size());
    }

    public BookResDto.BookRes findById(Long bookNo) {
        Book book = bookRepository.findById(bookNo).orElseThrow(() -> new IllegalArgumentException(bookNo + "번에 해당하는 도서가 없습니다."));
        return new BookResDto.BookRes(book);
    }

    @Transactional
    public BookResDto.BookRes updateBook(BookReqDto.UpdateBookReq req) {
        Book book = bookRepository.findById(req.getBookNo()).orElseThrow(() -> new IllegalArgumentException(req.getBookNo() + "번에 해당하는 도서가 없습니다."));
        book.updateBook(req.toEntity());
        return new BookResDto.BookRes(book);
    }

    @Transactional
    public void deleteByIdPhysical(Long bookNo) {
        log.info("================");
        Book book = bookRepository.findById(bookNo).orElseThrow(() -> new IllegalArgumentException(bookNo + "번에 해당하는 도서가 없습니다."));
        log.info("================");
        bookRepository.deleteByIdPhysical(book);
        log.info("여기서 커밋이 되는 것이 아니다.");

    }

    public void deleteByIdLogical(Long bookNo) {
        //변경감지를 사용한 update (setter 사용하지 않음!)
        Book book = bookRepository.findById(bookNo).orElseThrow(() -> new IllegalArgumentException(bookNo + "번에 해당하는 도서가 없습니다."));
        book.deleteByIdLogical(bookNo);


        //벌크연산을 활용한 update
//        bookRepository.deleteByIdLogical(bookNo);
    }

}
