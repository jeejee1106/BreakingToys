package com.toy.library.dto;

import com.toy.library.entity.Library;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class LibraryResDto {

    @NoArgsConstructor
    @Getter
    public static class LibraryRes {
        private Long libraryNo;
        private String name;
        private LocalDateTime createDt;
        private LocalDateTime updateDt;
        private String delYn;

        public LibraryRes(Library entity) {
            this.libraryNo = entity.getLibraryNo();
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
