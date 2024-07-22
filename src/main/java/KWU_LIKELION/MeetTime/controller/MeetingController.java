package KWU_LIKELION.MeetTime.controller;

import KWU_LIKELION.MeetTime.domain.Meeting;
import KWU_LIKELION.MeetTime.dto.MeetingRequest;
import KWU_LIKELION.MeetTime.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;

    @PostMapping(value = "/createMeeting")//meeting 생성
    public ResponseEntity<Long> createMeeting(@RequestBody MeetingRequest req)
    {
        Meeting meeting =meetingService.createMeeting(req);
        if(meeting!=null) {
            Long meetingId=meeting.getId();//meetingId 반환
            return ResponseEntity.ok(meetingId);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //selectTime(possibleTime)


    //showMeeting

    //showPossibleTime(to showMeeting)
}
