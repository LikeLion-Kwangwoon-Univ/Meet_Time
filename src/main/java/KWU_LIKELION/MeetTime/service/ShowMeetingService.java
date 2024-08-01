package KWU_LIKELION.MeetTime.service;

import KWU_LIKELION.MeetTime.domain.Meeting;
import KWU_LIKELION.MeetTime.domain.MeetingDay;
import KWU_LIKELION.MeetTime.dto.ShowMeetingPeopleResponse;
import KWU_LIKELION.MeetTime.dto.ShowMeetingResponse;
import KWU_LIKELION.MeetTime.repository.MeetingDayRepository;
import KWU_LIKELION.MeetTime.repository.MeetingRepository;
import KWU_LIKELION.MeetTime.repository.PossibleTimeRepository;
import KWU_LIKELION.MeetTime.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShowMeetingService {
    private final MeetingDayService meetingDayService;
    private final UserService userService;
    private final PossibleTimeService possibleTimeService;

    private final MeetingRepository meetingRepository;
    private final MeetingDayRepository meetingDayRepository;
    private final PossibleTimeRepository possibleTimeRepository;
    private final UsersRepository usersRepository;
    public ShowMeetingResponse showMeeting(Long meetingId){
        Meeting meeting=meetingRepository.findById(meetingId).get();

        List<MeetingDay> meetingDayList=meetingDayRepository.findAllByMeeting(meeting);


        Map<Long,List<Integer>> possibleTimeCountList=meetingDayList.stream()
                .collect(Collectors.toMap(
                        meetingDay -> meetingDay.getId(),
                        meetingDay -> possibleTimeService.getPossibleTimeCount(meetingDay)
                ));

        List<Long> meetingDayId = meetingDayService.getMeetingDayIdList(meeting);

        List<?> meetingList=meetingDayService.getMeetingList(meeting.getMeetingType(),meetingDayList);

        List<String> allPeople=userService.getAllUserName(meeting);

        return ShowMeetingResponse.fromEntity(meeting,meetingDayId,meetingList,allPeople,possibleTimeCountList);
    }

    public ShowMeetingPeopleResponse showMeetingPeople(Long meetingId, Long meetingDayId, Integer possibleTime){
        MeetingDay meetingDay=meetingDayRepository.findById(meetingDayId).get();

        List<String>possiblePeople=userService.getPossibleUserName(meetingDay,possibleTime);

        String meetingTitle=meetingRepository.findById(meetingId).get().getMeetingTitle();
        return ShowMeetingPeopleResponse.fromEntity(meetingId,meetingTitle,meetingDayId,possibleTime,possiblePeople);
    }
}
