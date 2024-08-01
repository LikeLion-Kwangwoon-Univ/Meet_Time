package KWU_LIKELION.MeetTime.dto;

import KWU_LIKELION.MeetTime.domain.Meeting;
import KWU_LIKELION.MeetTime.domain.MeetingType;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
public class BaseMeetingResponse<T> {

    private Long meetingId;

    private String meetingTitle;

    private MeetingType meetingType;

    private List<T> meetingList;

    private List<Long> meetingDayIdList;

    private LocalTime meetingStartTime;

    private LocalTime meetingEndTime;


    public void setFields(Meeting meeting, List<Long> meetingDayIdList,List<T> meetingList){
        this.meetingId = meeting.getId();
        this.meetingTitle = meeting.getMeetingTitle();
        this.meetingType = meeting.getMeetingType();
        this.meetingList=meetingList;
        this.meetingDayIdList = meetingDayIdList;
        this.meetingStartTime = meeting.getMeetingStartTime();
        this.meetingEndTime = meeting.getMeetingEndTime();
    }

    public static <T> BaseMeetingResponse<T> fromEntity(Meeting meeting,List<Long> meetingDayIdList,List<T>meetingList){
            return BaseMeetingResponse.<T>builder()
                    .meetingId(meeting.getId())
                    .meetingTitle(meeting.getMeetingTitle())
                    .meetingType(meeting.getMeetingType())
                    .meetingList(meetingList)
                    .meetingDayIdList(meetingDayIdList)
                    .meetingStartTime(meeting.getMeetingStartTime())
                    .meetingEndTime(meeting.getMeetingEndTime())
                    .build();
    }

}
