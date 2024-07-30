package KWU_LIKELION.MeetTime.dto;

import KWU_LIKELION.MeetTime.domain.Meeting;
import KWU_LIKELION.MeetTime.domain.MeetingType;
import KWU_LIKELION.MeetTime.domain.MeetingWeek;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Builder
public class CreateWeekMeetingResponse extends BaseMeetingResponse {

    private List<MeetingWeek> meetingList;

    public static CreateWeekMeetingResponse fromEntity(Meeting meeting, List<MeetingWeek> meetingList, List<Long> meetingDayIdList){
        CreateWeekMeetingResponse response=CreateWeekMeetingResponse.builder()
                .meetingList(meetingList)
                .build();

        response.setFields(meeting,meetingDayIdList);
        return response;

    }
}
