package KWU_LIKELION.MeetTime.dto;

import KWU_LIKELION.MeetTime.domain.Meeting;
import KWU_LIKELION.MeetTime.domain.MeetingDay;
import KWU_LIKELION.MeetTime.domain.MeetingType;
import KWU_LIKELION.MeetTime.domain.MeetingWeek;
import lombok.Builder;
import org.springframework.data.util.Pair;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public class MeetingRequest {

    private String meetingTitle;

    private MeetingType meetingType;

    private LocalDate day;

    private MeetingWeek week;

    private LocalTime meetingStartTime;

    private LocalTime meetingEndTime;

    public Pair<Meeting,MeetingDay> toEntity()
    {
        Meeting meeting=Meeting.builder()
                .meetingTitle(this.meetingTitle)
                .meetingType(this.meetingType)
                .meetingStartTime(this.meetingStartTime)
                .meetingEndTime(this.meetingEndTime)
                .build();
        MeetingDay meetingDay;
        if(this.meetingType==MeetingType.WEEK){
         meetingDay=MeetingDay.builder()
                 .week(this.week)
                 .meeting(meeting)
                 .build();
        }
        else{
            meetingDay=MeetingDay.builder()
                    .day(this.day)
                    .meeting(meeting)
                    .build();
        }

        return Pair.of(meeting,meetingDay);
    }
}
