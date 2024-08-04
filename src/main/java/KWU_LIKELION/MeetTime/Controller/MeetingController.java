package KWU_LIKELION.MeetTime.Controller;

import KWU_LIKELION.MeetTime.DTO.Meeting.*;
import KWU_LIKELION.MeetTime.Domain.Enum.mType;
import KWU_LIKELION.MeetTime.Domain.Meeting;
import KWU_LIKELION.MeetTime.Domain.MeetingDay;
import KWU_LIKELION.MeetTime.Service.MeetingDayService;
import KWU_LIKELION.MeetTime.Service.MeetingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static KWU_LIKELION.MeetTime.Domain.Meeting.newDayMeeting;
import static KWU_LIKELION.MeetTime.Domain.Meeting.newWeekMeeting;

@RestController
@RequiredArgsConstructor
public class MeetingController {
    public final MeetingService meetingService;
    public final MeetingDayService meetingDayService;

    @PostMapping("/meettime/day/create")
    public CreateDayMeetingResponse CreateDayMeeting(@RequestBody @Valid CreateDayMeetingRequest request){
        Meeting meeting = newDayMeeting(request.getMeeting_title(), request.getMeeting_start_time(), request.getMeeting_end_time());
        Long id = meetingService.createDayMeeting(meeting, request.getMeeting_list());

        List<Long> meetingDayIdList = new ArrayList<>();
        List<MeetingDay> meetingDayList = meetingDayService.findAllMeetingById(id);
        for(MeetingDay meetingDay : meetingDayList){
            meetingDayIdList.add(meetingDay.getId());
        }

        return new CreateDayMeetingResponse(id, meeting.getMeetingTitle(), meeting.getMeetingType(), request.getMeeting_list(),
                meetingDayIdList, meeting.getMeetingStartTime(), meeting.getMeetingEndTime(), meeting.getMeetingCreateTime());
    }


    @PostMapping("/meettime/week/create")
    public CreateWeekMeetingResponse CreateWeekMeeting(@RequestBody @Valid CreateWeekMeetingRequest request){
        Meeting meeting = newWeekMeeting(request.getMeeting_title(), request.getMeeting_start_time(), request.getMeeting_end_time());
        Long id = meetingService.createWeekMeeting(meeting, request.getMeeting_list());

        List<Long> meetingDayIdList = new ArrayList<>();
        List<MeetingDay> meetingDayList = meetingDayService.findAllMeetingById(id);
        for(MeetingDay meetingDay : meetingDayList){
            meetingDayIdList.add(meetingDay.getId());
        }

        return new CreateWeekMeetingResponse(id, meeting.getMeetingTitle(), meeting.getMeetingType(), request.getMeeting_list(),
                meetingDayIdList, meeting.getMeetingStartTime(), meeting.getMeetingEndTime(), meeting.getMeetingCreateTime());
    }


    @PostMapping("/meettime/join")
    public JoinMeetingResponse JoinMeeting(@RequestBody @Valid JoinMeetingRequest request){
        Meeting joinMeeting = meetingService.joinMeetingByCode(request.getMeeting_title(), request.getMeeting_id());

        List<LocalDate> meetingDayList = new ArrayList<>();
        List<Integer> meetingWeekList = new ArrayList<>();
        List<Long> meetingDayIdList = new ArrayList<>();
        List<MeetingDay> mList = meetingDayService.findAllMeetingById(joinMeeting.getId());
        for(MeetingDay meetingDay : mList){
            meetingDayIdList.add(meetingDay.getId());
            if(joinMeeting.getMeetingType().equals(mType.D)){
                meetingDayList.add(meetingDay.getMeetingDay());
            }
            else{
                meetingWeekList.add(meetingDay.getMeetingWeek());
            }
        }

        return new JoinMeetingResponse(joinMeeting.getId(), joinMeeting.getMeetingTitle(), joinMeeting.getMeetingType(),
                meetingDayList, meetingWeekList, meetingDayIdList,
                joinMeeting.getMeetingStartTime(), joinMeeting.getMeetingEndTime(), joinMeeting.getMeetingCreateTime());
    }
}
