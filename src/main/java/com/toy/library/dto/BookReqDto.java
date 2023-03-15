package com.toy.library.dto;

import com.toy.library.entity.Book;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class BookReqDto {

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public class SaveBookReq {

//        @NotNull(message = "libraryNo 값이 비어있음")
//        @Schema(example = "1", description = "도서관 고유 No")
//        private Library libraryNo;

        @NotNull(message = "title 값이 비어있음")
        @Schema(example = "책 제목 입니다.", description = "책 제목")
//        @Parameter(description = "책 제목", schema = @Schema(example = "JPA 파헤치기")) //스웨거 이거 대체 왜 안돼...?
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

//    @Getter
//    @Setter
//    @ToString
//    @NoArgsConstructor
//    public class DeleteBookReq {
//
//        @NotNull(message = "bookNo 값이 비어있음")
//        @Schema(example = "1", description = "도서 고유 No")
//        private Long bookNo;
//
//    }

}
