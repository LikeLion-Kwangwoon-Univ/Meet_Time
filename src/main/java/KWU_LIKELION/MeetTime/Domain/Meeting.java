package KWU_LIKELION.MeetTime.Domain;

import KWU_LIKELION.MeetTime.Domain.Enum.mType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
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

    private Integer meetingStartTime;

    private Integer meetingEndTime;

    private LocalDateTime meetingCreateTime;

    // 생성 메서드
    public static Meeting newDayMeeting(String name, Integer start, Integer end){
        Meeting meeting = new Meeting();
        meeting.setMeetingTitle(name);
        meeting.setMeetingType(mType.D);
        meeting.setMeetingStartTime(start);
        meeting.setMeetingEndTime(end);
        meeting.setMeetingCreateTime(LocalDateTime.now());

        return meeting;
    }

    public static Meeting newWeekMeeting(String name, Integer start, Integer end){
        Meeting meeting = new Meeting();
        meeting.setMeetingTitle(name);
        meeting.setMeetingType(mType.W);
        meeting.setMeetingStartTime(start);
        meeting.setMeetingEndTime(end);
        meeting.setMeetingCreateTime(LocalDateTime.now());

        return meeting;
    }

    public Integer meetingSize(){
        return meetingEndTime - meetingStartTime + 1;
    }
}
