package com.toy.library.service;

import com.toy.library.dto.LibraryDto;
import com.toy.library.entity.Library;
import com.toy.library.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;

    public LibraryDto.LibraryRes saveLibrary(LibraryDto.SaveLibraryReq libraryReqDto) {
        Library entity = libraryRepository.save(libraryReqDto.toEntity());
        return new LibraryDto.LibraryRes(entity);
    }

    public LibraryDto.SelectLibraryListRes findAll() {
        List<Library> list = libraryRepository.findAll();
        return new LibraryDto.SelectLibraryListRes(list, list.size());
    }

    public LibraryDto.LibraryRes findById(Long no) {
        Library entity = libraryRepository.findById(no).orElseThrow(() -> new IllegalArgumentException(no + "번에 해당하는 도서관이 없습니다."));
        return new LibraryDto.LibraryRes(entity);
    }
}
