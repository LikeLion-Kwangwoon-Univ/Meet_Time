package KWU_LIKELION.MeetTime.service;

import KWU_LIKELION.MeetTime.domain.Meeting;
import KWU_LIKELION.MeetTime.domain.MeetingDay;
import KWU_LIKELION.MeetTime.dto.MeetingRequest;
import KWU_LIKELION.MeetTime.repository.MeetingDayRepository;
import KWU_LIKELION.MeetTime.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final MeetingDayRepository meetingDayRepository;

    //getMeetingId

    //getMeeting


    //meeting 생성
    public Meeting createMeeting(MeetingRequest req)
    {
        Pair<Meeting,MeetingDay> saveEntity=req.toEntity();//meetingEntity 생성
        Meeting saveMeeting=saveEntity.getFirst();
        MeetingDay saveMeetingDay=saveEntity.getSecond();

        //Entity 저장
        meetingRepository.save(saveMeeting);
        meetingDayRepository.save(saveMeetingDay);
        return saveMeeting;
    }
    //setPossibleDay(meetingDay,meetingWeek)

    //setPossibleTimeRange(meetingStartTime,meetingEndTime)

    //getMeetingTimeRange


    //setPossibleTime
}
