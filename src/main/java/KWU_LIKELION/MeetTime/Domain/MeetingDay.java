package KWU_LIKELION.MeetTime.Domain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
public class MeetingDay {
    @Id @GeneratedValue
    @Column(name = "meeting_day_id")
    private Long id;

    private LocalDate meetingDay;

    @Enumerated(EnumType.STRING)
    private Week meetingWeek;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;
}
