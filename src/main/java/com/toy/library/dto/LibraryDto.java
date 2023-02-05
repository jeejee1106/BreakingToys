package com.toy.library.dto;

import com.toy.library.entity.Library;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class LibraryDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public class SaveLibraryReq {

        @NotNull(message = "name 값이 비어있음")
        @Schema(example = "뭐지", description = "도서관명")
        private String name;

        @NotNull(message = "delYn 값이 비어있음")
        @Schema(example = "뭐지", description = "삭제여부")
        private String delYn;

        public Library toEntity() {
            return Library.builder()
                    .name(name)
                    .delYn(delYn)
                    .build();
        }
    }


    @NoArgsConstructor
    @Setter
    public class SaveLibraryRes {

        private String name;
        private LocalDateTime createDt;
        private LocalDateTime updateDt;
        private String delYn;

    }

}
