package KWU_LIKELION.MeetTime.service;

import KWU_LIKELION.MeetTime.domain.*;
import KWU_LIKELION.MeetTime.dto.*;
import KWU_LIKELION.MeetTime.repository.MeetingDayRepository;
import KWU_LIKELION.MeetTime.repository.MeetingRepository;
import KWU_LIKELION.MeetTime.repository.PossibleTimeRepository;
import KWU_LIKELION.MeetTime.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final MeetingDayRepository meetingDayRepository;
    private final PossibleTimeRepository possibleTimeRepository;
    private final UsersRepository usersRepository;



    //meeting 생성
    public CreateDayMeetingResponse createDayMeeting(CreateDayMeetingRequest req)
    {
        Pair<Meeting, List<MeetingDay>> saveEntity=req.toEntity();//meetingEntity 생성
        Meeting saveMeeting=saveEntity.getFirst();
        List<MeetingDay> saveMeetingDay=saveEntity.getSecond();

        //Entity 저장
        meetingRepository.save(saveMeeting);
        meetingDayRepository.saveAll(saveMeetingDay);

        List<LocalDate> meetingList=saveMeetingDay.stream()
                .map(meetingDay -> meetingDay.getDay())
                .collect(Collectors.toList());

        List<Long> meetingDayIdList=meetingDayRepository.findAllMeetingDayIdByMeeting(saveMeeting);

        return CreateDayMeetingResponse.fromEntity(saveMeeting,meetingList,meetingDayIdList);
    }

    public CreateWeekMeetingResponse createWeekMeeting(CreateWeekMeetingRequest req)
    {
        Pair<Meeting, List<MeetingDay>> saveEntity=req.toEntity();//meetingEntity 생성
        Meeting saveMeeting=saveEntity.getFirst();
        List<MeetingDay> saveMeetingDay=saveEntity.getSecond();

        //Entity 저장
        meetingRepository.save(saveMeeting);
        meetingDayRepository.saveAll(saveMeetingDay);

        List<MeetingWeek> meetingList=saveMeetingDay.stream()
                .map(meetingDay -> meetingDay.getWeek())
                .collect(Collectors.toList());
        List<Long> meetingDayIdList=meetingDayRepository.findAllMeetingDayIdByMeeting(saveMeeting);

        return CreateWeekMeetingResponse.fromEntity(saveMeeting,meetingList,meetingDayIdList);
    }


    //possibleTime 생성 및 삭제
    public List<PossibleTime> setPossibleTime(PossibleTimeRequest req)
    {
        //update라면 기본에 있던 possibleTime 삭제 후 새로 생성
        if(deletePossibleTime(req.getUserId())){

            List<PossibleTime> possibleTimeList=req.toEntity();

            possibleTimeRepository.saveAll(possibleTimeList);
            return possibleTimeList;
        }else{
            return null;
        }
    }

    //기존 possbileTime 삭제
    public Boolean deletePossibleTime(Long userId)
    {
        Users user=usersRepository.findById(userId).orElse(null);
        try{
            List<PossibleTime> deletePossibleTimeList=possibleTimeRepository.findALlByUsers(user);
            if(deletePossibleTimeList==null){//possibleTime update가 아닌 새로 생성이라면 true 반환
                return true;
            }
            //기존에 있던 정보 삭제
            deletePossibleTimeList.forEach(possibleTime -> possibleTimeRepository.delete(possibleTime));
            return true;
        }catch (Exception e){
            return false;
        }

    }


    //로그인 이후의 화면 meeting 관련 데이터 전달
    public MeetingByUserResponse getMeetingByUser(Users user){
       return MeetingByUserResponse.of(user);
    }

    //결과 관련 데이터 전달
//    public MeetingResponse showMeeting(Long meetingId){
//        return MeetingResponse.of(meetingId);
//    }

    //가능한 사람 데이터를 전달하는 함수 필요



}
