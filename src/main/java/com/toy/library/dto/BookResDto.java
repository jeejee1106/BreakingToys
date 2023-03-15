package com.toy.library.dto;

import com.toy.library.entity.Book;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class BookResDto {

    @NoArgsConstructor
    @Getter
    public static class BookRes {
//        private Library libraryNo;
        private Long bookNo;
        private String title;
        private String loanStatusYn;
        private String lossYn;
        private String rsvStatusYn;
        private LocalDateTime createDt;
        private LocalDateTime updateDt;
        private String delYn;

        public BookRes(Book entity) {
//            this.libraryNo = entity.getLibraryNo();
            this.bookNo = entity.getBookNo();
            this.title = entity.getTitle();
            this.loanStatusYn = entity.getLoanStatusYn();
            this.lossYn = entity.getLossYn();
            this.rsvStatusYn = entity.getRsvStatusYn();
            this.createDt = entity.getCreateDt();
            this.updateDt = entity.getUpdateDt();
            this.delYn = entity.getDelYn();
        }
    }

    @NoArgsConstructor
    @Getter
    public static class SelectBookListRes {

        private List<Book> list;
        private int totalCount;

        public SelectBookListRes(List<Book> list, int totalCount) {
            this.list = list;
            this.totalCount = totalCount;
        }
    }

}
