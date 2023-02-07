package com.toy.library.controller;

import com.toy.library.dto.LibraryDto;
import com.toy.library.service.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    @Operation(summary = "도서관 등록")
    @PostMapping
    public LibraryDto.SaveLibraryRes saveLibrary(LibraryDto.SaveLibraryReq req) {
        LibraryDto.SaveLibraryRes res = libraryService.saveLibrary(req);
        return res;
    }

    @Operation(summary = "도서관 목록 전체 조회")
    @GetMapping
    public LibraryDto.SelectLibraryListRes findById() {
        LibraryDto.SelectLibraryListRes list = libraryService.findAll();
        return list;
    }

}
