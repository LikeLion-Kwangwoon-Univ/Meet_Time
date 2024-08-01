package KWU_LIKELION.MeetTime.controller;

import KWU_LIKELION.MeetTime.dto.ShowMeetingPeopleResponse;
import KWU_LIKELION.MeetTime.dto.ShowMeetingResponse;
import KWU_LIKELION.MeetTime.service.ShowMeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/meetingtime/show")
@Controller
@RequiredArgsConstructor
public class ShowMeetingController {
    private final ShowMeetingService showMeetingService;
    @GetMapping("/{meetingId}")
    public ResponseEntity<ShowMeetingResponse> showMeeting(@PathVariable Long meetingId){
        ShowMeetingResponse res=showMeetingService.showMeeting(meetingId);

        if(res!=null){
            return ResponseEntity.ok(res);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{meetingId}/{meetingDayId}/{possibleTime}")
    public ResponseEntity<ShowMeetingPeopleResponse> showMeeting(@PathVariable Long meetingId, Long meetingDayId, Integer possibleTime){
        ShowMeetingPeopleResponse res=showMeetingService.showMeetingPeople(meetingId,meetingDayId,possibleTime);
        if(res!=null){
            return ResponseEntity.ok(res);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
