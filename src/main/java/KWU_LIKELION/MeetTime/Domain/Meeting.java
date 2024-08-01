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

    // Meeting의 가능한 possibleTime 크기
    public Integer meetingSizePossibleTime( ){
        Duration duration = Duration.between(getMeetingStartTime(), getMeetingEndTime());
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        if(minutes != 0){
            hours++;
        }

        return (int) hours;
    }
}
