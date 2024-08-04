package KWU_LIKELION.MeetTime.Controller;

import KWU_LIKELION.MeetTime.DTO.Result.MeetingResultResponse;
import KWU_LIKELION.MeetTime.DTO.Result.PossibleTimeResultResponse;
import KWU_LIKELION.MeetTime.Domain.Enum.mType;
import KWU_LIKELION.MeetTime.Domain.Meeting;
import KWU_LIKELION.MeetTime.Domain.MeetingDay;
import KWU_LIKELION.MeetTime.Domain.Users;
import KWU_LIKELION.MeetTime.Service.MeetingDayService;
import KWU_LIKELION.MeetTime.Service.MeetingService;
import KWU_LIKELION.MeetTime.Service.PossibleTimeService;
import KWU_LIKELION.MeetTime.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class ResultController {
    private final MeetingService meetingService;
    private final MeetingDayService meetingDayService;
    private final UserService userService;
    private final PossibleTimeService possibleTimeService;

    @GetMapping("/meettime/show/{meeting_id}")
    public MeetingResultResponse MeetingResult(@PathVariable("meeting_id") Long meeting_id){
        Optional<Meeting> findMeeting = meetingService.findMeetingById(meeting_id);
        Meeting meeting = findMeeting.orElseThrow(() -> new IllegalStateException("존재하지 않는 MeetingID"));

        List<LocalDate> meetingDayList = new ArrayList<>();
        List<Integer> meetingWeekList = new ArrayList<>();
        List<Long> meetingDayIdList = new ArrayList<>();

        List<MeetingDay> mList = meetingDayService.findAllMeetingById(meeting.getId());
        for(MeetingDay meetingDay : mList){
            meetingDayIdList.add(meetingDay.getId());
            if(meeting.getMeetingType().equals(mType.D)){
                meetingDayList.add(meetingDay.getMeetingDay());
            }
            else{
                meetingWeekList.add(meetingDay.getMeetingWeek());
            }
        }

        List<Users> userList = userService.findAllUsersByMeetingId(meeting.getId());
        List<String> allUsers = new ArrayList<>();
        for(Users user : userList){
            allUsers.add(user.getNickname());
        }

        Map<Long, List<Integer>> possibleCount = new HashMap<>();
        for(Long meetingDayId : meetingDayIdList){
            List<Integer> countList = possibleTimeService.countUserMeetingDay(meetingDayId);
            possibleCount.put(meetingDayId, countList);
        }

        return new MeetingResultResponse(meeting.getId(), meeting.getMeetingTitle(), meeting.getMeetingType(),
                meetingDayList, meetingWeekList, meetingDayIdList,
                meeting.getMeetingStartTime(), meeting.getMeetingEndTime(), meeting.getMeetingCreateTime(),
                allUsers, possibleCount);
    }


    @GetMapping("/meettime/show/{meeting_id}/{meeting_day_id}/{possible_id}")
    public PossibleTimeResultResponse PossibleTimeResult(@PathVariable("meeting_id") Long meeting_id,
                                                         @PathVariable("meeting_day_id") Long meeting_day_id,
                                                         @PathVariable("possible_id") Integer possible_id){

        Optional<Meeting> findMeeting = meetingService.findMeetingById(meeting_id);
        Meeting meeting = findMeeting.orElseThrow(() -> new IllegalStateException("존재하지 않는 MeetingID"));

        List<Users> possibleUserList = possibleTimeService.allUsersInPossibleTimeNum(meeting_day_id, possible_id);
        List<String> possibleUsers = new ArrayList<>();
        for(Users user : possibleUserList){
            possibleUsers.add(user.getNickname());
        }

        return new PossibleTimeResultResponse(meeting.getId(), meeting.getMeetingTitle(),
                meeting_day_id, possible_id, possibleUsers);
    }

}
