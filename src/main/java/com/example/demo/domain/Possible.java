package com.example.demo.domain;

//import javax.persistence.*;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class Possible {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "meetingDay_id")
    private MeetingDay meetingDay;

    private LocalTime possibleTime;

}
