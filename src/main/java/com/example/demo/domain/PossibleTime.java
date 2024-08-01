package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
public class PossibleTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "possible_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meetingDay_id")
    private MeetingDay meetingDay;

    private LocalTime possibleTime;
}
