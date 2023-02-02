package com.toy.library.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime createDt;

    @Column(nullable = false)
    private LocalDateTime updateDt;

    @Column(nullable = false)
    private String delYn;

    @Builder
    public Library(Long no, String name, LocalDateTime createDt, LocalDateTime updateDt, String delYn) {
        this.no = no;
        this.name = name;
        this.createDt = createDt;
        this.updateDt = updateDt;
        this.delYn = delYn;
    }
}
