package com.toy.library.controller;

import com.toy.library.dto.BookReqDto;
import com.toy.library.dto.BookResDto;
import com.toy.library.entity.Book;
import com.toy.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Operation(summary = "도서 등록")
    @PostMapping
    public BookResDto.BookRes saveBook(@Valid BookReqDto.SaveBookReq req) {
        log.info(">>>>requestParam = {}", req);
        return bookService.saveBook(req);
    }

    @Operation(summary = "도서 목록 전체 조회")
    @GetMapping
    public BookResDto.SelectBookListRes findAll() {
        return bookService.findAll();
    }

    @Operation(summary = "고유No로 도서 조회")
    @GetMapping("/{bookNo}")
    public BookResDto.BookRes findById(@PathVariable @Valid Long bookNo) {
        return bookService.findById(bookNo);
    }

    @Operation(summary = "도서 삭제(물리)")
    @DeleteMapping("/physical/{bookNo}")
    public void deleteByIdPhysical(@PathVariable @Valid Long bookNo) {
        bookService.deleteByIdPhysical(bookNo);
    }

    @Operation(summary = "도서 삭제(논리)")
    @DeleteMapping("/logical/{bookNo}")
    public void deleteByIdLogical(@PathVariable @Valid Long bookNo) {
        bookService.deleteByIdLogical(bookNo);
    }

}
