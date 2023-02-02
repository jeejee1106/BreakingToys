package com.toy.library.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
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

}
