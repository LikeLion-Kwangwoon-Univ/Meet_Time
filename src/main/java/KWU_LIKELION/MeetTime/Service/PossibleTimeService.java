package KWU_LIKELION.MeetTime.Service;

import KWU_LIKELION.MeetTime.Domain.MeetingDay;
import KWU_LIKELION.MeetTime.Domain.PossibleTime;
import KWU_LIKELION.MeetTime.Domain.Users;
import KWU_LIKELION.MeetTime.Repository.MeetingDayRepository;
import KWU_LIKELION.MeetTime.Repository.PossibleTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static KWU_LIKELION.MeetTime.Domain.PossibleTime.newPossibleTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PossibleTimeService {
    private final PossibleTimeRepository possibleTimeRepository;
    private final MeetingDayRepository meetingDayRepository;

    // User 가능한 모든 시간 저장
    @Transactional
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
    public List<Integer> countUserMeetingDay(Long meetingDayId){
        Optional<MeetingDay> meetingDay = meetingDayRepository.findById(meetingDayId);
        MeetingDay findMeetingDay = meetingDay.get();

        List<Integer> countUserList = new ArrayList<>();
        Integer startIndex = findMeetingDay.getMeeting().getMeetingStartTime();

        for(int i = 0; i < findMeetingDay.getMeeting().meetingSize(); i++){
            int countPossibleTime = countUserPossibleTime(meetingDayId, startIndex + i);
            countUserList.add(countPossibleTime);
        }

        return countUserList;
    }

    // MeetingDay의 possibleNum을 선택한 인원수
    public Integer countUserPossibleTime(Long meetingDayId, Integer possibleNum){
        List<PossibleTime> meetingDayPossibleTimeList = possibleTimeRepository.findByMeetingDayId(meetingDayId);

        Integer count = 0;
        for(PossibleTime possible : meetingDayPossibleTimeList){
            if(possible.getPossible().equals(possibleNum)){
                count++;
            }
        }

        return count;
    }

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
