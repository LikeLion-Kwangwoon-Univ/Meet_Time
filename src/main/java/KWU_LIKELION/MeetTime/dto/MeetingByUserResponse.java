package KWU_LIKELION.MeetTime.dto;

import KWU_LIKELION.MeetTime.domain.Meeting;
import KWU_LIKELION.MeetTime.domain.MeetingDay;
import KWU_LIKELION.MeetTime.domain.PossibleTime;
import KWU_LIKELION.MeetTime.domain.Users;
import KWU_LIKELION.MeetTime.repository.MeetingDayRepository;
import KWU_LIKELION.MeetTime.repository.PossibleTimeRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class MeetingByUserResponse {

    private static MeetingDayRepository meetingDayRepository;
    private static PossibleTimeRepository possibleTimeRepository;


    private Meeting meeting;

    private List<Pair<MeetingDay,List<PossibleTime>>> possibleTimeList; //이게 맞아?


    public static MeetingByUserResponse of(Users user){
        Meeting meetingByUser=user.getMeeting();
        List<MeetingDay> meetingDayList=meetingDayRepository.findAllByMeeting(meetingByUser);
        List<Pair<MeetingDay,List<PossibleTime>>> possibleTimeList= meetingDayList.stream()
                .map(m->
                        Pair.of(m,possibleTimeRepository.findAllByMeetingDay(m))
                )
                .collect(Collectors.toList());

        return MeetingByUserResponse.builder()
                .meeting(meetingByUser)
                .possibleTimeList(possibleTimeList)
                .build();
    }
}
