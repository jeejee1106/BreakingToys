package com.toy.library.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    private String userId;
    private String password;
    private String name;
    private String department;
    private String position;
    private String role;
    private int loanAvailability;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;
    private String delYn;

}
