package com.toy.library.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Loan {

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
    private LocalDateTime loanDt;

    private LocalDateTime returnDt;

    @Column(nullable = false)
    private LocalDateTime dueDt;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private int overDueDay;

    @Builder

    public Loan(Long no, Book book, User user, LocalDateTime loanDt, LocalDateTime dueDt, int status, int overDueDay) {
        this.no = no;
        this.book = book;
        this.user = user;
        this.loanDt = loanDt;
        this.dueDt = dueDt;
        this.status = status;
        this.overDueDay = overDueDay;
    }
}
