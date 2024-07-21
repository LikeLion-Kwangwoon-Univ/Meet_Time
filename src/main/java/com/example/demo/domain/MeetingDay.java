package com.example.demo.domain;

import com.example.demo.domain.enums.MeetingWeek;
import jakarta.persistence.*;

//import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class MeetingDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meeting_day_id")
    private Long id;

    private LocalDate meetingday;

    @Enumerated(EnumType.STRING)
    private MeetingWeek meetingWeek;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;
}
