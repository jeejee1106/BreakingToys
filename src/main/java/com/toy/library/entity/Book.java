package com.toy.library.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    private Long libraryNo;
    private String title;
    private String statusYn;
    private String rsvStatusYn;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;
    private String delYn;

}
