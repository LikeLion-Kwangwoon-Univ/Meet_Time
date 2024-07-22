package KWU_LIKELION.MeetTime.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MeetingDay")
@Builder
public class MeetingDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="meetingDay_id")
    private Long id;

    @Column
    private LocalDate day;

    @Column
    private MeetingWeek week;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="meeting_id")
    private Meeting meeting;
}
