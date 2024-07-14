package KWU_LIKELION.MeetTime.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MeetingDay")
public class MeetingDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="meetingDay_id")
    private Long id;

    @Column
    private LocalDate meetingDay;

    @Column
    private MeetingWeek meetingWeek;

    @ManyToOne
    @Column(nullable = false)
    private Long meeting_id;
}
