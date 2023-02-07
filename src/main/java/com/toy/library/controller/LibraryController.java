package com.toy.library.controller;

import com.toy.library.dto.LibraryDto;
import com.toy.library.service.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    @Operation(summary = "도서관 등록")
    @PostMapping
    public LibraryDto.LibraryRes saveLibrary(LibraryDto.SaveLibraryReq req) {
        return libraryService.saveLibrary(req);
    }

    @Operation(summary = "도서관 목록 전체 조회")
    @GetMapping
    public LibraryDto.SelectLibraryListRes findAll() {
        return libraryService.findAll();
    }

    @Operation(summary = "고유No로 도서관 조회")
    @GetMapping("/{no}")
    public LibraryDto.LibraryRes findById(@PathVariable Long no) {
        return libraryService.findById(no);
    }

}
