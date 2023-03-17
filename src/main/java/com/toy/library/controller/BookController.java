package com.toy.library.controller;

import com.toy.library.dto.BookReqDto;
import com.toy.library.dto.BookResDto;
import com.toy.library.entity.Book;
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
        log.info(">>>>requestParam = {}", req);
        return bookService.saveBook(req);
    }

    @Operation(summary = "도서 목록 전체 조회")
    @GetMapping
    public BookResDto.SelectBookListRes findAll() {
        return bookService.findAll();
    }

    @Operation(summary = "고유No로 도서 조회")
    @GetMapping("/{no}")
    public BookResDto.BookRes findById(@PathVariable @Valid Long no) {
        return bookService.findById(no);
    }
    
//    @Operation(summary = "도서 전체 삭제")
//    @DeleteMapping
//    public ResponseEntity<Map<String, Object>> deleteAll() {
//        return bookService.deleteAll();
//    }
    
    @Operation(summary = "deleteById로 도서관 삭제")
    @DeleteMapping("/{no}")
    public void deleteById(@PathVariable @Valid Long no) {
        bookService.deleteById(no);
    }
    
//    @Operation(summary = "delete로 도서관 삭제")
//    @DeleteMapping("/{no}")
//    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long no) {
//        return bookService.delete(no);
//    }

}
