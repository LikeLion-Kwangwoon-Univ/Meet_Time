package KWU_LIKELION.MeetTime.Controller;

import KWU_LIKELION.MeetTime.DTO.User.LoginUserRequest;
import KWU_LIKELION.MeetTime.DTO.User.LoginUserResponse;
import KWU_LIKELION.MeetTime.DTO.User.UserSelectRequest;
import KWU_LIKELION.MeetTime.DTO.User.UserSelectResponse;
import KWU_LIKELION.MeetTime.Domain.Enum.mType;
import KWU_LIKELION.MeetTime.Domain.Meeting;
import KWU_LIKELION.MeetTime.Domain.MeetingDay;
import KWU_LIKELION.MeetTime.Domain.Users;
import KWU_LIKELION.MeetTime.Service.MeetingDayService;
import KWU_LIKELION.MeetTime.Service.MeetingService;
import KWU_LIKELION.MeetTime.Service.PossibleTimeService;
import KWU_LIKELION.MeetTime.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final MeetingService meetingService;
    private final MeetingDayService meetingDayService;
    private final PossibleTimeService possibleTimeService;

    @PostMapping("/meettime/{meeting_id}/login")
    public LoginUserResponse JoinMeetingUser(@PathVariable("meeting_id") Long meeting_id,
                                             @RequestBody @Valid LoginUserRequest request){
        Users user = new Users();
        user.setNickname(request.getNickname());
        user.setPassword(request.getPassword());
        Long user_id = userService.loginUser(user, meeting_id);

        return new LoginUserResponse(meeting_id, user_id, user.getNickname(), user.getPassword());
    }


    @GetMapping("/meettime/select/{meeting_id}/{user_id}")
    public UserSelectResponse userSelect(@PathVariable("meeting_id") Long meeting_id,
                                         @PathVariable("user_id") Long user_id){

        Optional<Meeting> findMeeting = meetingService.findMeetingById(meeting_id);
        Meeting meeting = findMeeting.orElseThrow(() -> new IllegalStateException("존재하지 않는 MeetingID"));

        Optional<Users> findUser = userService.findUserById(user_id);
        Users user = findUser.orElseThrow(() -> new IllegalStateException("존재하지 않는 UserID"));

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

        Map<Long, List<Integer>> pList = new HashMap<>();
        for(Long meetingDayId : meetingDayIdList){
            List<Integer> possibleList = possibleTimeService.allPossibleTimeNum(meetingDayId, user.getId());
            pList.put(meetingDayId, possibleList);
        }

        return new UserSelectResponse(meeting.getId(), meeting.getMeetingTitle(), meeting.getMeetingType(),
                meetingDayList, meetingWeekList, meetingDayIdList, meeting.getMeetingStartTime(),
                meeting.getMeetingEndTime(), meeting.getMeetingCreateTime(),
                user.getId(), user.getNickname(), pList);
    }

    @PostMapping("/meettime/select/{meeting_id}/{user_id}")
    public UserSelectResponse userSelect(@PathVariable("meeting_id") Long meeting_id,
                                         @PathVariable("user_id") Long user_id,
                                         @RequestBody @Valid UserSelectRequest request){

        Optional<Meeting> findMeeting = meetingService.findMeetingById(meeting_id);
        Meeting meeting = findMeeting.orElseThrow(() -> new IllegalStateException("존재하지 않는 MeetingID"));

        Optional<Users> findUser = userService.findUserById(user_id);
        Users user = findUser.orElseThrow(() -> new IllegalStateException("존재하지 않는 UserID"));

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

        for(Long meetingDayId : meetingDayIdList){
            MeetingDay meetingDay = meetingDayService.findMeetingDayById(meetingDayId).get();
            possibleTimeService.possibleTimeAdd(user, meetingDay, request.getPossible_list().get(meetingDayId));
        }

        Map<Long, List<Integer>> pList = new HashMap<>();
        for(Long meetingDayId : meetingDayIdList){
            List<Integer> possibleList = possibleTimeService.allPossibleTimeNum(meetingDayId, user.getId());
            pList.put(meetingDayId, possibleList);
        }

        return new UserSelectResponse(meeting.getId(), meeting.getMeetingTitle(), meeting.getMeetingType(),
                meetingDayList, meetingWeekList, meetingDayIdList, meeting.getMeetingStartTime(),
                meeting.getMeetingEndTime(), meeting.getMeetingCreateTime(),
                user.getId(), user.getNickname(), pList);
    }

}
