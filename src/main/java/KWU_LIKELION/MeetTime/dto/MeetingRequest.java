package KWU_LIKELION.MeetTime.dto;

import KWU_LIKELION.MeetTime.domain.Meeting;
import KWU_LIKELION.MeetTime.domain.MeetingDay;
import KWU_LIKELION.MeetTime.domain.MeetingType;
import KWU_LIKELION.MeetTime.domain.MeetingWeek;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.util.Pair;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
@Builder
public class MeetingRequest {

    private String meetingTitle;

    private MeetingType meetingType;

    private List<LocalDate> day;

    private List<MeetingWeek> week;

    private LocalTime meetingStartTime;

    private LocalTime meetingEndTime;

    public Pair<Meeting,List<MeetingDay>> toEntity()
    {
        Meeting meeting=Meeting.builder()
                .meetingTitle(this.meetingTitle)
                .meetingType(this.meetingType)
                .meetingStartTime(this.meetingStartTime)
                .meetingEndTime(this.meetingEndTime)
                .build();
        if(this.meetingType==MeetingType.WEEK){
            List<MeetingDay> meetingDay=this.week
                    .stream()
                    .map(m->MeetingDay.builder()
                            .week(m)
                            .meeting(meeting)
                            .build())
                    .collect(Collectors.toList());
            return Pair.of(meeting,meetingDay);
        }
        else{
            List<MeetingDay> meetingDay=this.day
                    .stream()
                    .map(m->MeetingDay.builder()
                            .day(m)
                            .meeting(meeting)
                            .build())
                    .collect(Collectors.toList());
            return Pair.of(meeting,meetingDay);
        }

    }
}
