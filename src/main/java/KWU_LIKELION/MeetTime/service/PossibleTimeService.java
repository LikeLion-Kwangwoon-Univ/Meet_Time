package KWU_LIKELION.MeetTime.service;

import KWU_LIKELION.MeetTime.domain.*;
import KWU_LIKELION.MeetTime.dto.GetPossibleTimeResponse;
import KWU_LIKELION.MeetTime.dto.PossibleTimeRequest;
import KWU_LIKELION.MeetTime.repository.MeetingDayRepository;
import KWU_LIKELION.MeetTime.repository.MeetingRepository;
import KWU_LIKELION.MeetTime.repository.PossibleTimeRepository;
import KWU_LIKELION.MeetTime.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PossibleTimeService {
    private final MeetingDayService meetingDayService;
    private final PossibleTimeRepository possibleTimeRepository;
    private final UsersRepository usersRepository;
    private final MeetingDayRepository meetingDayRepository;
    private final MeetingRepository meetingRepository;

    @Transactional(readOnly = true)
    public GetPossibleTimeResponse getPossibleTime(Long meetingId,Long userId){
        Meeting meeting=meetingRepository.findById(meetingId).get();
        Users user=usersRepository.findById(userId).get();

        List<MeetingDay> meetingDayList=meetingDayRepository.findAllByMeeting(meeting);


        Map<Long,List<Integer>> possibleTimeList= meetingDayList.stream()
                .collect(Collectors.toMap(
                        meetingDay->meetingDay.getId(),
                        meetingDay -> possibleTimeRepository.findAllPossibleTimeByMeetingDayAndUser(meetingDay,user)
                ));
        List<Long> meetingDayId = meetingDayService.getMeetingDayIdList(meeting);

        return GetPossibleTimeResponse.fromEntity(user.getId(),user.getUserName()
                ,possibleTimeList,meeting,meetingDayId,meetingDayService.getMeetingList(meeting.getMeetingType(),meetingDayList));

    }

    //get possibleTime count
    @Transactional(readOnly = true)
    public List<Integer> getPossibleTimeCount(MeetingDay meetingDay){
       return possibleTimeRepository.getPossibletimeCount(meetingDay);
    }

    //possibleTime 생성 및 삭제
    public List<PossibleTime> setPossibleTime(PossibleTimeRequest req)
    {
//        //update라면 기본에 있던 possibleTime 삭제 후 새로 생성
//        if(deletePossibleTime(req.getUserId())){
//
//            List<PossibleTime> possibleTimeList=req.toEntity();
//
//            possibleTimeRepository.saveAll(possibleTimeList);
//            return possibleTimeList;
//        }else{
//            return null;
//        }
    }

    //기존 possbileTime 삭제
    public Boolean deletePossibleTime(Long userId)
    {
//        Users user=usersRepository.findById(userId).orElse(null);
//        try{
//            List<PossibleTime> deletePossibleTimeList=possibleTimeRepository.findALlByUsers(user);
//            if(deletePossibleTimeList==null){//possibleTime update가 아닌 새로 생성이라면 true 반환
//                return true;
//            }
//            //기존에 있던 정보 삭제
//            deletePossibleTimeList.forEach(possibleTime -> possibleTimeRepository.delete(possibleTime));
//            return true;
//        }catch (Exception e){
//            return false;
//        }

    }
}
