package com.toy.library.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String department;

    private String position;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private int loanAvailability;

    @Column(nullable = false)
    private LocalDateTime createDt;

    @Column(nullable = false)
    private LocalDateTime updateDt;

    @Column(nullable = false)
    private String delYn;

    @Builder
    public User(Long no, String userId, String password, String name, String role, int loanAvailability, LocalDateTime createDt, LocalDateTime updateDt, String delYn) {
        this.no = no;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.role = role;
        this.loanAvailability = loanAvailability;
        this.createDt = createDt;
        this.updateDt = updateDt;
        this.delYn = delYn;
    }
}
