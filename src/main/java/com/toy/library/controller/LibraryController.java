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

    /**
     * 도서관 삭제는 deleteById 또는 delete 두 개 중 아무거나 사용.
     * 둘 다 작동원리는 같음. return하는 오류 코드만 다름.
     * @param no
     */
//    @Operation(summary = "deleteById로 도서관 삭제")
//    @DeleteMapping("/{no}")
//    public void deleteById(@PathVariable Long no) {
//        libraryService.deleteById(no);
//    }

    @Operation(summary = "delete로 도서관 삭제")
    @DeleteMapping("/{no}")
    public void delete(@PathVariable Long no) {
        libraryService.delete(no);
    }

}
