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
public class PossibleTimeRequest {

    private final UsersRepository usersRepository;
    private final MeetingDayRepository meetingDayRepository;
    private final PossibleTimeRepository possibleTimeRepository;

    private Long userId;

    private List<Pair<Long,List<LocalTime>>> possibleTimeList;
    private List<LocalTime> possibleTime;

    public List<PossibleTime> toEntity(){
        //possibleTime user_id 찾기
        Users userEntity=usersRepository.findById(userId).get();

        //PossibleTime Entity 생성
        List<PossibleTime> possibleTimeEntity=this.possibleTimeList.stream()
                .flatMap(pair->
                    pair.getSecond().stream().map(possibleTime->{
                    //possibleTime만 추출
                    MeetingDay meetingDayEntity=meetingDayRepository.findById(pair.getFirst()).get();
                    //중복된 데이터 검사 -> 이렇게 밖에 못하나?
                    if(!possibleTimeRepository.existsByUsersAndPossibleTimeAndMeetingDay(userEntity,possibleTime,meetingDayEntity)){
                        //user와 possibleTime,meetingDay가 중복되지 않을 시 possibleTime entity 생성
                        return PossibleTime.builder()
                                .users(userEntity)
                                .meetingDay(meetingDayRepository.findById(pair.getFirst()).get())
                                .possibleTime(possibleTime)
                                .build();
                    }else{
                        return null;
                    }
                })).filter(e->e!=null)
                .collect(Collectors.toList());


        return possibleTimeEntity;
    }
}
