package com.toy.library.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @ManyToOne
    @JoinColumn(name = "libraryNo")
    private Library library;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String loanStatusYn;

    @Column(nullable = false)
    private String rsvStatusYn;

    @Column(nullable = false)
    private LocalDateTime createDt;

    @Column(nullable = false)
    private LocalDateTime updateDt;

    @Column(nullable = false)
    private String delYn;

    @Builder
    public Book(Long no, Library library, String title, String loanStatusYn, String rsvStatusYn, LocalDateTime createDt, LocalDateTime updateDt, String delYn) {
        this.no = no;
        this.library = library;
        this.title = title;
        this.loanStatusYn = loanStatusYn;
        this.rsvStatusYn = rsvStatusYn;
        this.createDt = createDt;
        this.updateDt = updateDt;
        this.delYn = delYn;
    }
}
