package com.toy.library.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    private Long bookNo;
    private Long userNo;
    private LocalDateTime loanDt;
    private LocalDateTime returnDt;
    private LocalDateTime dueDt;
    private int status;
    private int overDueDay;

}
