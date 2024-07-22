package KWU_LIKELION.MeetTime.dto;

import KWU_LIKELION.MeetTime.domain.PossibleTime;
import KWU_LIKELION.MeetTime.domain.Users;
import KWU_LIKELION.MeetTime.repository.MeetingDayRepository;
import KWU_LIKELION.MeetTime.repository.PossibleTimeRepository;
import KWU_LIKELION.MeetTime.repository.UsersRepository;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class PossibleTimeRequest {

    private final UsersRepository usersRepository;
    private final MeetingDayRepository meetingDayRepository;
    private final PossibleTimeRepository possibleTimeRepository;

    private Long userId;

    private Long meetingDayId;
    private List<LocalTime> possibleTime;

    public List<PossibleTime> toEntity(){
        Users userEntity=usersRepository.findById(userId).get();
        List<PossibleTime> possibleTimeList=this.possibleTime.stream()
                .map(m->{
                    if(!possibleTimeRepository.existsByUsersAndPossibleTime(userEntity,m)){
                        return PossibleTime.builder()
                                .users(userEntity)
                                .meetingDay(meetingDayRepository.findById(meetingDayId).get())
                                .possibleTime(m)
                                .build();
                    }else{
                        return null;
                    }
                })
                .filter(e->e!=null)//수정 필요
                .collect(Collectors.toList());
        return  possibleTimeList;
    }
}
