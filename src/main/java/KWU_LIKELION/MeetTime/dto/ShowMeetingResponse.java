package KWU_LIKELION.MeetTime.dto;

import KWU_LIKELION.MeetTime.domain.Meeting;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@SuperBuilder
@NoArgsConstructor

public class ShowMeetingResponse extends BaseMeetingResponse{

    private List<String> allPeople;
    private Map<Long,List<Integer>> possibleTimeList;


    public static ShowMeetingResponse fromEntity(Meeting meeting, List<Long> meetingDayIdList, List<?> meetingList,
                                                        List<String> allPeople,Map<Long,List<Integer>> possibleTimeList){
        ShowMeetingResponse res=ShowMeetingResponse.builder()
                        .allPeople(allPeople)
                                .possibleTimeList(possibleTimeList)
                                        .build();

        res.setFields(meeting,meetingDayIdList,meetingList);
        return res;
    }

}
