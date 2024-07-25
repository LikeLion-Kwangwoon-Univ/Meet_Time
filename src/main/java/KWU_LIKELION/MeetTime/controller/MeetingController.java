package KWU_LIKELION.MeetTime.controller;

import KWU_LIKELION.MeetTime.domain.Meeting;
import KWU_LIKELION.MeetTime.dto.CreateMeetingRequest;
import KWU_LIKELION.MeetTime.dto.MeetingResponse;
import KWU_LIKELION.MeetTime.dto.PossibleTimeRequest;
import KWU_LIKELION.MeetTime.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;

    @PostMapping(value = "/createMeeting")//meeting 생성
    public ResponseEntity<Long> createMeeting(@RequestBody CreateMeetingRequest req)
    {
        Meeting meeting =meetingService.createMeeting(req);
        if(meeting!=null) {
            Long meetingId=meeting.getId();//meetingId 반환
            return ResponseEntity.ok(meetingId);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value = "/selectTime")//가능한 시간 선택
    public ResponseEntity<String> selectTime(@RequestBody PossibleTimeRequest req){
        if(meetingService.setPossibleTime(req)!=null) {
            return ResponseEntity.ok("성공");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @GetMapping(value = "/{meetingId}")//결과 보여주는 화면(아직 미완성)
    public ResponseEntity<MeetingResponse> showMeeting(@PathVariable Long meetingId){
        MeetingResponse meetingResponse=meetingService.showMeeting(meetingId);
        if(meetingResponse!=null){
            return ResponseEntity.ok(meetingResponse);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //showPossiblePerson(to showMeeting)
}
