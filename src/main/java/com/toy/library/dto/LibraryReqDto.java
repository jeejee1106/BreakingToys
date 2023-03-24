package com.toy.library.dto;

import com.toy.library.entity.Library;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

public class LibraryReqDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public class SaveLibraryReq {

        @NotNull(message = "name 값이 비어있음")
        @Schema(example = "서울도서관", description = "도서관명")
        private String name;

        @NotNull(message = "delYn 값이 비어있음")
        @Schema(example = "N", description = "삭제여부")
        private String delYn;

        public Library toEntity() {
            return Library.builder()
                    .name(name)
                    .delYn(delYn)
                    .build();
        }
    }

}
