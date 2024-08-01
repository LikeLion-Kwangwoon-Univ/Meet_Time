package KWU_LIKELION.MeetTime.controller;

import KWU_LIKELION.MeetTime.dto.GetPossibleTimeResponse;
import KWU_LIKELION.MeetTime.service.PossibleTimeService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class PossibleTimeController {
    private final PossibleTimeService possibleTimeService;
    @GetMapping("/select/{meetingId}/{userId}")
    public ResponseEntity<GetPossibleTimeResponse> getPossibleTime(@PathVariable Long meetingId,Long userId){
        GetPossibleTimeResponse res= possibleTimeService.getPossibleTime(meetingId,userId);

        if(res!=null){
            return ResponseEntity.ok(res);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
