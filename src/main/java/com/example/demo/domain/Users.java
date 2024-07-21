package com.example.demo.domain;

//import javax.persistence.*;

import jakarta.persistence.*;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String nickname;
    private String password;

}
