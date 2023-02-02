package com.toy.library.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    private Long bookNo;
    private Long userNo;
    private int status;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;

}
