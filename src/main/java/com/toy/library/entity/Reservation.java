package com.toy.library.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @ManyToOne
    @JoinColumn(name = "bookNo")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "userNo")
    private User user;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private LocalDateTime createDt;

    @Column(nullable = false)
    private LocalDateTime updateDt;

    @Builder
    public Reservation(Long no, Book book, User user, int status, LocalDateTime createDt, LocalDateTime updateDt) {
        this.no = no;
        this.book = book;
        this.user = user;
        this.status = status;
        this.createDt = createDt;
        this.updateDt = updateDt;
    }
}
