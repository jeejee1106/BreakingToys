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

    public LibraryResDto.SelectLibraryListRes findAll() {
        List<Library> list = libraryRepository.findAll();
        return new LibraryResDto.SelectLibraryListRes(list, list.size());
    }

    public LibraryResDto.LibraryRes findById(Long libraryNo) {
        Library entity = libraryRepository.findById(libraryNo).orElseThrow(() -> new IllegalArgumentException(libraryNo + "번에 해당하는 도서관이 없습니다."));
        return new LibraryResDto.LibraryRes(entity);
    }

    @Transactional
    public void deleteByIdPhysical(Long libraryNo) {
        Library library = libraryRepository.findById(libraryNo).orElseThrow(() -> new IllegalArgumentException(libraryNo + "번에 해당하는 도서관이 없습니다."));
        libraryRepository.deleteByIdPhysical(library);

    }
}
