package KWU_LIKELION.MeetTime.Service;

import KWU_LIKELION.MeetTime.Domain.MeetingDay;
import KWU_LIKELION.MeetTime.Domain.PossibleTime;
import KWU_LIKELION.MeetTime.Domain.Users;
import KWU_LIKELION.MeetTime.Repository.PossibleTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

import static KWU_LIKELION.MeetTime.Domain.PossibleTime.newPossibleTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PossibleTimeService {
    private final PossibleTimeRepository possibleTimeRepository;

    // User 가능한 모든 시간 저장
    public void possibleTimeAdd(Users user, MeetingDay meetingDay, List<Integer> possibleNumList){
        for(Integer possibleNum : possibleNumList){
            PossibleTime possibleTime = newPossibleTime(possibleNum);
            possibleTime.setUser(user);
            possibleTime.setMeetingDay(meetingDay);

            possibleTimeRepository.save(possibleTime);
        }
    }

    // User가 가지는 모든 possibleTime
    public List<PossibleTime> findAllPossibleTimeByUserId(Long userId){
        return possibleTimeRepository.findByUserId(userId);
    }

    // User가 meetingDay에서 가능한 시간 번호
    public List<Integer> allPossibleTimeNum(Long meetingDayId,Long userId){
        List<PossibleTime> userPossibleTimeList = findAllPossibleTimeByUserId(userId);

        List<Integer> allPossibleTimeList = new ArrayList<>();
        for(PossibleTime possible : userPossibleTimeList){
            if(possible.getMeetingDay().getId().equals(meetingDayId)){
                allPossibleTimeList.add(possible.getPossible());
            }
        }

        return allPossibleTimeList;
    }

    // meetingDay 모든 PossibleTime 가능한 사용자 숫자

    // meetingDay 특정 시간 번호에서 가능한 사용자
    public List<Users> allUsersInPossibleTimeNum(Long meetingDayId, Integer possibleNum){
        List<PossibleTime> meetingDayPossibleTimeList = possibleTimeRepository.findByMeetingDayId(meetingDayId);

        List<Users> availableUsers = new ArrayList<>();
        for(PossibleTime possible : meetingDayPossibleTimeList){
            if(possible.getPossible().equals(possibleNum)){
                availableUsers.add(possible.getUser());
            }
        }

        return availableUsers;
    }
}
