package KWU_LIKELION.MeetTime.dto;

import KWU_LIKELION.MeetTime.domain.Meeting;
import KWU_LIKELION.MeetTime.domain.MeetingDay;
import KWU_LIKELION.MeetTime.domain.MeetingType;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Builder
public class CreateDayMeetingResponse extends BaseMeetingResponse {

    private List<LocalDate> meetingList;

    public static CreateDayMeetingResponse fromEntity(Meeting meeting, List<LocalDate> meetingList,List<Long> meetingDayIdList){
        CreateDayMeetingResponse response=CreateDayMeetingResponse.builder()
                .meetingList(meetingList)
                .build();
        response.setFields(meeting,meetingDayIdList);
        return response;


    }

}
