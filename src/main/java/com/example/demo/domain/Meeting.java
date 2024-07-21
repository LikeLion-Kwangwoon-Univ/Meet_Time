package com.example.demo.domain;

import com.example.demo.domain.enums.MeetingType;
import jakarta.persistence.*;

//import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meeting_id")
    private Long id;

    private String meetingTitle;

    @Enumerated(EnumType.STRING)
    private MeetingType meetingType;

    private LocalTime meetingStartTime;
    private LocalTime meetingEndTime;
}
