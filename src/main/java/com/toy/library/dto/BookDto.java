package com.toy.library.dto;

import com.toy.library.entity.Book;
import com.toy.library.entity.Library;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class BookDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public class SaveBookReq {

//        @NotNull(message = "libraryNo 값이 비어있음")
//        @Schema(example = "1", description = "도서관 고유 No")
//        private Library libraryNo;

        @NotNull(message = "title 값이 비어있음")
        @Schema(example = "책 제목 입니다.", description = "책 제목")
        private String title;

        @NotNull(message = "loanStatusYn 값이 비어있음")
        @Schema(example = "N", description = "대출여부")
        private String loanStatusYn;

        @NotNull(message = "lossYn 값이 비어있음")
        @Schema(example = "N", description = "분실여부")
        private String lossYn;

        @NotNull(message = "rsvStatusYn 값이 비어있음")
        @Schema(example = "N", description = "예약여부")
        private String rsvStatusYn;

        @NotNull(message = "delYn 값이 비어있음")
        @Schema(example = "N", description = "삭제여부")
        private String delYn;

        public Book toEntity() {
            return Book.builder()
//                    .libraryNo(libraryNo)
                    .title(title)
                    .loanStatusYn(loanStatusYn)
                    .lossYn(lossYn)
                    .rsvStatusYn(rsvStatusYn)
                    .delYn(delYn)
                    .build();
        }
    }

    @NoArgsConstructor
    @Getter
    public static class BookRes {
        private Library libraryNo;
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
