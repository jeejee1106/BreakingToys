package com.toy.library.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Book extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookNo;

    //얘만 있으면 다대일 단방향 연관관계.
//    @ManyToOne
//    @JoinColumn(name = "libraryNo")
//    private Library libraryNo;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String loanStatusYn;

    @Column(nullable = false)
    private String lossYn;

    @Column(nullable = false)
    private String rsvStatusYn;

    @Column(nullable = false)
    private String delYn;

    @Builder
    public Book(String title, String loanStatusYn, String lossYn, String rsvStatusYn, LocalDateTime createDt, LocalDateTime updateDt, String delYn) {
//        this.libraryNo = libraryNo;
        this.title = title;
        this.loanStatusYn = loanStatusYn;
        this.lossYn = lossYn;
        this.rsvStatusYn = rsvStatusYn;
        this.delYn = delYn;
    }

    public Book(Long bookNo) {
        this.bookNo = bookNo;
    }
}
