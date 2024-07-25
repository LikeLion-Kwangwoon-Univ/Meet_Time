package KWU_LIKELION.MeetTime.dto;

import KWU_LIKELION.MeetTime.domain.Meeting;
import KWU_LIKELION.MeetTime.domain.MeetingDay;
import KWU_LIKELION.MeetTime.domain.PossibleTime;
import KWU_LIKELION.MeetTime.repository.MeetingDayRepository;
import KWU_LIKELION.MeetTime.repository.MeetingRepository;
import KWU_LIKELION.MeetTime.repository.PossibleTimeRepository;
import KWU_LIKELION.MeetTime.repository.UsersRepository;
import lombok.Builder;
import org.springframework.data.util.Pair;

import java.time.LocalTime;
import java.util.List;

@Builder
public class MeetingResponse {//meeting 결과 response
    private MeetingRepository meetingRepository;
    private MeetingDayRepository meetingDayRepository;
    private PossibleTimeRepository possibleTimeRepository;
    private UsersRepository usersRepository;

    private Meeting meeting;



    private List<Pair<MeetingDay,List<LocalTime>>> possibleTimeList;

//    public MeetingResponse of(Long meetingId){
//        Meeting meetingEntity=meetingRepository.findById(meetingId).orElse(null);
//        List<MeetingDay> meetingDaysEntity=meetingDayRepository.findAllByMeeting(meetingEntity);
//        if(meetingDaysEntity!=null){
//            meetingDaysEntity.stream()
//                    .map(meetingDay -> {
//
//                    })
//        }
//
//    }
}
