package KWU_LIKELION.MeetTime.Domain;
import KWU_LIKELION.MeetTime.Domain.Enum.Week;
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

    // 생성 메서드
    public static MeetingDay newDayMeetingDay(LocalDate day, Meeting meeting){
        MeetingDay meetingDay = new MeetingDay();
        meetingDay.setMeetingDay(day);
        meetingDay.setMeeting(meeting);

        return meetingDay;
    }

    public static MeetingDay newWeekMeetingDay(Week week, Meeting meeting){
        MeetingDay meetingDay = new MeetingDay();
        meetingDay.setMeetingWeek(week);
        meetingDay.setMeeting(meeting);

        return meetingDay;
    }
}
