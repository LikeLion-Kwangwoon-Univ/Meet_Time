package KWU_LIKELION.MeetTime.service;

import KWU_LIKELION.MeetTime.domain.Meeting;
import KWU_LIKELION.MeetTime.domain.MeetingDay;
import KWU_LIKELION.MeetTime.dto.BaseMeetingResponse;
import KWU_LIKELION.MeetTime.repository.MeetingDayRepository;
import KWU_LIKELION.MeetTime.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class JoinService{
    private final MeetingRepository meetingRepository;
    private final MeetingDayRepository meetingDayRepository;
    private final MeetingDayService meetingDayService;
    public BaseMeetingResponse joinMeeting(String meetingTitle,Long meetingId){
        Meeting meeting=meetingRepository.findById(meetingId).get();
        List<MeetingDay> meetingDay=meetingDayRepository.findAllByMeeting(meeting);
        List<Long> meetingDayIdList= meetingDayRepository.findAllMeetingDayIdByMeeting(meeting);
        List<?>meetingList=meetingDayService.getMeetingList(meeting.getMeetingType(),meetingDay);

        return BaseMeetingResponse.fromEntity(meeting,meetingDayIdList,meetingList);


    }
}
