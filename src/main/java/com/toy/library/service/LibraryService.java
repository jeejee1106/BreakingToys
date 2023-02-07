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

    public LibraryDto.SaveLibraryRes saveLibrary(LibraryDto.SaveLibraryReq libraryReqDto) {
        Library entity = libraryRepository.save(libraryReqDto.toEntity());
        return new LibraryDto.SaveLibraryRes(entity);
    }

    public LibraryDto.SelectLibraryListRes findAll() {
        List<Library> list = libraryRepository.findAll();
        return new LibraryDto.SelectLibraryListRes(list, list.size());
    }
}
