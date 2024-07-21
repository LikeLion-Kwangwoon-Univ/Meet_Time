package com.example.demo.domain;

//import javax.persistence.*;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class PossibleTime {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
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
