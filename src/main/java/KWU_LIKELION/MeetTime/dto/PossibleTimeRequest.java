package KWU_LIKELION.MeetTime.dto;

import KWU_LIKELION.MeetTime.domain.MeetingDay;
import KWU_LIKELION.MeetTime.domain.PossibleTime;
import KWU_LIKELION.MeetTime.domain.Users;
import KWU_LIKELION.MeetTime.repository.MeetingDayRepository;
import KWU_LIKELION.MeetTime.repository.PossibleTimeRepository;
import KWU_LIKELION.MeetTime.repository.UsersRepository;
import lombok.Data;
import org.springframework.data.util.Pair;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PossibleTimeRequest {//가능한 시간 선택 request

    private final UsersRepository usersRepository;
    private final MeetingDayRepository meetingDayRepository;
    private final PossibleTimeRepository possibleTimeRepository;

    private Long userId;

    //List<pair<meetingId,List<possibleTime>>
    private List<Pair<Long,List<LocalTime>>> possibleTimeList;

    public List<PossibleTime> toEntity(){
        //possibleTime user_id 찾기
        Users userEntity=usersRepository.findById(userId).get();

        //PossibleTime Entity 생성
        List<PossibleTime> possibleTimeEntity=this.possibleTimeList.stream()
                .flatMap(pair->
                    pair.getSecond().stream().map(possibleTime->{
                    //possibleTime만 추출
                        return PossibleTime.builder()
                            .users(userEntity)
                            .meetingDay(meetingDayRepository.findById(pair.getFirst()).get())
                            .possibleTime(possibleTime)
                            .build();

                })).filter(e->e!=null)
                .collect(Collectors.toList());


        return possibleTimeEntity;
    }
}
