package com.toy.library.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
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

}
