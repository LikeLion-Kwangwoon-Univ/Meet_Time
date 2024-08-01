package KWU_LIKELION.MeetTime.dto;

import KWU_LIKELION.MeetTime.domain.Meeting;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class GetPossibleTimeResponse extends BaseMeetingResponse {
    private Long userId;
    private String userName;
    private Map<Long, List<Integer>> possibleTimeList;

    public static GetPossibleTimeResponse fromEntity(Long userId, String userName,
                                 Map<Long, List<Integer>> possibleTimeList, Meeting meeting,
                                 List<Long> meetingDayList, List<?> meetingList){
        GetPossibleTimeResponse res=GetPossibleTimeResponse.builder()
                .userId(userId)
                .userName(userName)
                .possibleTimeList(possibleTimeList)
                .build();
        res.setFields(meeting,meetingDayList,meetingList);
        return res;
    }


}

