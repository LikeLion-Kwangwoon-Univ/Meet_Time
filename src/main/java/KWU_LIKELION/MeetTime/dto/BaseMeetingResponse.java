package KWU_LIKELION.MeetTime.dto;

import KWU_LIKELION.MeetTime.domain.Meeting;
import KWU_LIKELION.MeetTime.domain.MeetingType;
import lombok.Builder;

import java.time.LocalTime;
import java.util.List;

@Builder
public class BaseMeetingResponse {

    private Long meetingId;

    private String meetingTitle;

    private MeetingType meetingType;

    private List<Long> meetingDayIdList;

    private LocalTime meetingStartTime;

    private LocalTime meetingEndTime;

    public void setFields(Meeting meeting, List<Long> meetingDayIdList){
        this.meetingId = meeting.getId();
        this.meetingTitle = meeting.getMeetingTitle();
        this.meetingType = meeting.getMeetingType();
        this.meetingDayIdList = meetingDayIdList;
        this.meetingStartTime = meeting.getMeetingStartTime();
        this.meetingEndTime = meeting.getMeetingEndTime();
    }

}
