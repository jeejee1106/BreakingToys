package com.toy.library.controller;

import com.toy.library.dto.LibraryDto;
import com.toy.library.service.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    @Operation(summary = "도서관 등록")
    @GetMapping
    public String saveLibrary(LibraryDto.SaveLibraryReq libraryReqDto) {
        log.info("현재시간={}", LocalDateTime.now());
        libraryService.saveLibrary(libraryReqDto);
        return "도서관 저장 완료";
    }

}
