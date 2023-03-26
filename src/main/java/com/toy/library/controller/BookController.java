package com.toy.library.controller;

import com.toy.library.dto.BookReqDto;
import com.toy.library.dto.BookResDto;
import com.toy.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Operation(summary = "도서 등록")
    @PostMapping
    public BookResDto.BookRes saveBook(@Valid BookReqDto.SaveBookReq req) {
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

    @Operation(summary = "도서 수정")
    @PatchMapping
    public BookResDto.BookRes updateBook(@Valid BookReqDto.UpdateBookReq req) {
        return bookService.updateBook(req);
    }

    @Operation(summary = "도서 삭제(물리)")
    @DeleteMapping("/physical/{bookNo}")
    public void deleteByIdPhysical(@PathVariable @Valid Long bookNo) {
        bookService.deleteByIdPhysical(bookNo);
        log.info("여기서 커밋이 된다.");
    }

    @Operation(summary = "도서 삭제(논리)")
    @DeleteMapping("/logical/{bookNo}")
    public void deleteByIdLogical(@PathVariable @Valid Long bookNo) {
        bookService.deleteByIdLogical(bookNo);
    }

}
