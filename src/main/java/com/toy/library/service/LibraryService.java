package com.toy.library.service;

import com.toy.library.dto.LibraryReqDto;
import com.toy.library.dto.LibraryResDto;
import com.toy.library.entity.Library;
import com.toy.library.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;

    @Transactional
    public LibraryResDto.LibraryRes saveLibrary(LibraryReqDto.SaveLibraryReq req) {
        Library library = libraryRepository.save(req.toEntity());
        return new LibraryResDto.LibraryRes(library);
    }

//    public LibraryResDto.SelectLibraryListRes findAll() {
//        List<Library> list = libraryRepository.findAll();
//        return new LibraryDto.SelectLibraryListRes(list, list.size());
//    }

//    public LibraryResDto.LibraryRes findById(Long no) {
//        Library entity = libraryRepository.findById(no).orElseThrow(() -> new IllegalArgumentException(no + "번에 해당하는 도서관이 없습니다."));
//        return new LibraryDto.LibraryRes(entity);
//    }

    /**
     * 리턴타입 고민해보기
     * ResponseEntity : 상태코드도 직접 지정해줄 수 있고, HttpHeader? Body?에 정보를 더 담을 수 있다.
     * Map : 반환에 필요한 정보만 담을 수 있다.
     * 또 뭐가 있을까
     * @return
     */
//    public ResponseEntity<Map<String, Object>> deleteAll() {
//        Map<String, Object> map = new HashMap<>();
//        libraryRepository.deleteAll();
//        map.put("code", "200");
//        map.put("message", "ok");
//
//        return new ResponseEntity<>(map, HttpStatus.OK);
//    }

    /**
     * @param no
     * deleteById메서드 안에 delete메서드가 들어 있고, 그 안에서 또 findById메서드를 사용한다.
     * 장단점 : 로직이 메서드 하나로 끝나지만 내부적으로 처리되는 예외처리 방식을 따라야한다. (메시지 커스텀 못함)
     * 즉, 내부적으로 null체크를 해준다. (no값을 찾을 수 없을 경우 EmptyResultDataAccessException 을 발생시킴)
     * null입력 시 500에러 발생
     * 흠 삭제 성공했을 땐 returnCode와 returnMessage를 따로 반환해주고 싶은데...흠...
     */
//    public void deleteById(Long no) {
//        libraryRepository.deleteById(no);
//    }

    /**
     * @param no
     * 이렇게 직접 delete 메서드와 findById메서드를 조합해서 쓸 수도 있다.
     * 장단점 : 로직이 좀 더 길어지지만 예외처리를 커스텀하여 개발자가 원하는 Exception과 메시지를 전달할 수 있다.
     * null입력 시  ExHandler로 지정한 400에러(BAD_REQUEST) 발생
     *
     * 흠 삭제 성공했을 땐 returnCode와 returnMessage를 따로 반환해주고 싶은데...흠...
     * 리턴타입 고민해보기
     */
//    public ResponseEntity<Map<String, Object>> delete(Long no) {
//        Map<String, Object> map = new HashMap<>();
//
//        libraryRepository.delete(
//                libraryRepository.findById(no).orElseThrow(() -> new IllegalArgumentException("삭제실패. 데이터를 찾을 수 없음"))
//        );
//
//        map.put("code", "200");
//        map.put("message", "ok");
//
//        return new ResponseEntity<>(map, HttpStatus.OK);
//
//    }
}
