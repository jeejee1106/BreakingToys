package com.toy.library.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BookHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookHistoryNo;

    @Column(nullable = false)
    private Long bookNo;

    @Column(nullable = false)
    private Long libraryNo;

    @Column(nullable = false)
    private String lossYn;

    @Column(nullable = false)
    private LocalDateTime createDt;

}
