package KWU_LIKELION.MeetTime.controller;

import KWU_LIKELION.MeetTime.dto.BaseMeetingResponse;
import KWU_LIKELION.MeetTime.dto.CreateDayMeetingRequest;
import KWU_LIKELION.MeetTime.dto.CreateWeekMeetingRequest;
import KWU_LIKELION.MeetTime.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/meettime")
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;

    @PostMapping(value = "/day/create")//meeting 생성
    public ResponseEntity<BaseMeetingResponse> createDayMeeting(@RequestBody CreateDayMeetingRequest req)
    {
        BaseMeetingResponse res =meetingService.createDayMeeting(req);
        if(res!=null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value = "/week/create")//meeting 생성
    public ResponseEntity<BaseMeetingResponse> createMeeting(@RequestBody CreateWeekMeetingRequest req)
    {
        BaseMeetingResponse res=meetingService.createWeekMeeting(req);
        if(res!=null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
