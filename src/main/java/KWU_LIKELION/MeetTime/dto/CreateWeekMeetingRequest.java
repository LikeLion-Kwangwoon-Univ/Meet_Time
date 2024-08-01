package KWU_LIKELION.MeetTime.dto;

import KWU_LIKELION.MeetTime.domain.Meeting;
import KWU_LIKELION.MeetTime.domain.MeetingDay;
import KWU_LIKELION.MeetTime.domain.MeetingType;
import KWU_LIKELION.MeetTime.domain.MeetingWeek;
import org.springframework.data.util.Pair;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class CreateWeekMeetingRequest {
    private String meetingTitle;

    private MeetingType meetingType;

    private List<MeetingWeek> meetingList;

    private LocalTime meetingStartTime;

    private LocalTime meetingEndTime;


    public Meeting toMeetingEntity(){
        return Meeting.builder()
                .meetingTitle(this.meetingTitle)
                .meetingType(this.meetingType)
                .meetingStartTime(this.meetingStartTime)
                .meetingEndTime(this.meetingEndTime)
                .build();
    }


    public List<MeetingDay> toMeetingWeekEntity(Meeting meeting){
        return this.meetingList
                .stream()
                .map(m->MeetingDay.builder()
                        .week(m)
                        .meeting(meeting)
                        .build())
                .collect(Collectors.toList());
    }
}
