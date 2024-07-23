package KWU_LIKELION.MeetTime.service;

import KWU_LIKELION.MeetTime.domain.*;
import KWU_LIKELION.MeetTime.dto.CreateMeetingRequest;
import KWU_LIKELION.MeetTime.dto.MeetingByUserResponse;
import KWU_LIKELION.MeetTime.dto.PossibleTimeRequest;
import KWU_LIKELION.MeetTime.repository.MeetingDayRepository;
import KWU_LIKELION.MeetTime.repository.MeetingRepository;
import KWU_LIKELION.MeetTime.repository.PossibleTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final MeetingDayRepository meetingDayRepository;
    private final PossibleTimeRepository possibleTimeRepository;

    //getMeetingId

    //getMeeting


    //meeting 생성
    public Meeting createMeeting(CreateMeetingRequest req)
    {
        Pair<Meeting, List<MeetingDay>> saveEntity=req.toEntity();//meetingEntity 생성
        Meeting saveMeeting=saveEntity.getFirst();
        List<MeetingDay> saveMeetingDay=saveEntity.getSecond();

        //Entity 저장
        meetingRepository.save(saveMeeting);
        meetingDayRepository.saveAll(saveMeetingDay);

        return saveMeeting;
    }

    public List<PossibleTime> setPossibleTime(PossibleTimeRequest req)
    {
        List<PossibleTime> possibleTimeList=req.toEntity();

        possibleTimeRepository.saveAll(possibleTimeList);
        return possibleTimeList;
    }

    //deletePossibleTime

    public Pair<LocalTime,LocalTime> getMeetingTimeRange(Long meetingId){
        Meeting meetingEntity=meetingRepository.findById(meetingId).get();
        LocalTime startTime=meetingEntity.getMeetingStartTime();
        LocalTime endTime=meetingEntity.getMeetingEndTime();

        return Pair.of(startTime,endTime);
    }

    public MeetingByUserResponse getMeetingByUser(Users user){
       return MeetingByUserResponse.of(user);
    }



}
