package KWU_LIKELION.MeetTime.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class ShowMeetingPeopleResponse {

    private Long meetingId;

    private String meetingTitle;

    private Long meetingDayId;

    private Integer possibleId;

    private List<String> possiblePeople;

    public static ShowMeetingPeopleResponse fromEntity(Long meetingId,String meetingTitle,Long meetingDayId,Integer possibleId,List<String> possiblePeople){
        return ShowMeetingPeopleResponse.builder()
                .meetingId(meetingId)
                .meetingTitle(meetingTitle)
                .meetingDayId(meetingDayId)
                .possibleId(possibleId)
                .possiblePeople(possiblePeople)
                .build();
    }
}
