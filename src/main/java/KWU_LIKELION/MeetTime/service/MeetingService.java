package KWU_LIKELION.MeetTime.service;

import KWU_LIKELION.MeetTime.domain.*;
import KWU_LIKELION.MeetTime.dto.*;
import KWU_LIKELION.MeetTime.repository.MeetingDayRepository;
import KWU_LIKELION.MeetTime.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final PossibleTimeService possibleTimeService;
    private final MeetingDayService meetingDayService;

    private final MeetingRepository meetingRepository;
    private final MeetingDayRepository meetingDayRepository;


    //meeting 생성
    public BaseMeetingResponse createDayMeeting(CreateDayMeetingRequest req)
    {
        Meeting meeting=req.toMeetingEntity();
        List<MeetingDay> meetingDayList=req.toMeetingDayEntity(meeting);

        //Entity 저장
        meetingRepository.save(meeting);
        meetingDayRepository.saveAll(meetingDayList);

        List<LocalDate> meetingList=meetingDayList.stream()
                .map(meetingDay -> meetingDay.getDay())
                .collect(Collectors.toList());

        List<Long> meetingDayIdList=meetingDayService.getMeetingDayIdList(meeting);

        return BaseMeetingResponse.fromEntity(meeting,meetingDayIdList,meetingList);
    }

    public BaseMeetingResponse createWeekMeeting(CreateWeekMeetingRequest req)
    {

        Meeting meeting=req.toMeetingEntity();
        List<MeetingDay> meetingDayList=req.toMeetingWeekEntity(meeting);

        //Entity 저장
        meetingRepository.save(meeting);
        meetingDayRepository.saveAll(meetingDayList);

        List<MeetingWeek> meetingList=meetingDayList.stream()
                .map(meetingDay -> meetingDay.getWeek())
                .collect(Collectors.toList());
        List<Long> meetingDayIdList=meetingDayRepository.findAllMeetingDayIdByMeeting(meeting);

        return BaseMeetingResponse.fromEntity(meeting,meetingDayIdList,meetingList);
    }






}
