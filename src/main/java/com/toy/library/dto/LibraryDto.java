package com.toy.library.dto;

import com.toy.library.entity.Library;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

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
    @Getter
    public static class LibraryRes {
        private Long no;
        private String name;
        private LocalDateTime createDt;
        private LocalDateTime updateDt;
        private String delYn;

        public LibraryRes(Library entity) {
            this.no = entity.getNo();
            this.name = entity.getName();
            this.createDt = entity.getCreateDt();
            this.updateDt = entity.getUpdateDt();
            this.delYn = entity.getDelYn();
        }
    }

    @NoArgsConstructor
    @Getter
    public static class SelectLibraryListRes {

        private List<Library> list;
        private int totalCount;

        public SelectLibraryListRes(List<Library> list, int totalCount) {
            this.list = list;
            this.totalCount = totalCount;
        }
    }

}
