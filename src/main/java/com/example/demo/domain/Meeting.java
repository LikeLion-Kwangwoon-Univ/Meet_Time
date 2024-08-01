package com.example.demo.domain;

import com.example.demo.domain.enums.MeetingType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
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

    private LocalDateTime meetingCreateTime;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    private List<MeetingDay> meetingDays;
}
