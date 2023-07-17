package com.toy.library.controller;

import com.toy.library.dto.LibraryReqDto;
import com.toy.library.dto.LibraryResDto;
import com.toy.library.service.LibraryService;
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
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    @Operation(summary = "도서관 등록")
    @PostMapping
    public LibraryResDto.LibraryRes saveLibrary(LibraryReqDto.SaveLibraryReq req) {
        return libraryService.saveLibrary(req);
    }

    @Operation(summary = "도서관 목록 전체 조회")
    @GetMapping
    public LibraryResDto.SelectLibraryListRes findAll() {
        return libraryService.findAll();
    }

    @Operation(summary = "고유No로 도서관 조회")
    @GetMapping("/{libraryNo}")
    public LibraryResDto.LibraryRes findById(@PathVariable Long libraryNo) {
        return libraryService.findById(libraryNo);
    }

    @Operation(summary = "도서관 삭제(물리)")
    @DeleteMapping("/physical/{libraryNo}")
    public void deleteByIdPhysical(@PathVariable Long libraryNo) {
        libraryService.deleteByIdPhysical(libraryNo);
    }
}
