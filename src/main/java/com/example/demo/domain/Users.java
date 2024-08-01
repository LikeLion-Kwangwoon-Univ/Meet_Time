package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String nickname;
    private String password;

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;
}
