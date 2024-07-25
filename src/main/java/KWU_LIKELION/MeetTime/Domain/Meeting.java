package KWU_LIKELION.MeetTime.Domain;

import KWU_LIKELION.MeetTime.Domain.Enum.mType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter @Setter
public class Meeting {
    @Id @GeneratedValue
    @Column(name = "meeting_id")
    private Long id;

    private String meetingTitle;

    @Enumerated(EnumType.STRING)
    private mType meetingType;

    private LocalTime meetingStartTime;

    private LocalTime meetingEndTime;

    private LocalDateTime meetingCreateTime;

    // 생성 메서드
    public static Meeting newDayMeeting(String name, LocalTime start, LocalTime end){
        Meeting meeting = new Meeting();
        meeting.setMeetingTitle(name);
        meeting.setMeetingType(mType.DAY);
        meeting.setMeetingStartTime(start);
        meeting.setMeetingEndTime(end);
        meeting.setMeetingCreateTime(LocalDateTime.now());

        return meeting;
    }

    public static Meeting newWeekMeeting(String name, LocalTime start, LocalTime end){
        Meeting meeting = new Meeting();
        meeting.setMeetingTitle(name);
        meeting.setMeetingType(mType.WEEk);
        meeting.setMeetingStartTime(start);
        meeting.setMeetingEndTime(end);
        meeting.setMeetingCreateTime(LocalDateTime.now());

        return meeting;
    }
}
