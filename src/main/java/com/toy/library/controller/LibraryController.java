package com.toy.library.controller;

import com.toy.library.dto.LibraryDto;
import com.toy.library.service.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
     * 리턴타입 고민해보기
     * ResponseEntity : 상태코드도 직접 지정해줄 수 있고, HttpHeader? Body?에 정보를 더 담을 수 있다.
     * Map : 반환에 필요한 정보만 담을 수 있다.
     * 또 뭐가 있을까
     * @return
     */
    @Operation(summary = "도서관 전체 삭제")
    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteAll() {
        return libraryService.deleteAll();
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

    /**
     * 리턴타입 고민해보기
     * ResponseEntity : 상태코드도 직접 지정해줄 수 있고, HttpHeader? Body?에 정보를 더 담을 수 있다.
     * Map : 반환에 필요한 정보만 담을 수 있다.
     * 또 뭐가 있을까
     * @return
     */
    @Operation(summary = "delete로 도서관 삭제")
    @DeleteMapping("/{no}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long no) {
        return libraryService.delete(no);
    }

}
