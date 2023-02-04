package com.toy.library.service;

import com.toy.library.dto.LibraryDto;
import com.toy.library.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibraryService {

    private LibraryRepository libraryRepository;

    public void saveLibrary(LibraryDto.SaveLibraryReq libraryReqDto) {
        libraryRepository.save(libraryReqDto.toEntity());
    }
}
