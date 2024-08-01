package KWU_LIKELION.MeetTime.controller;


import KWU_LIKELION.MeetTime.dto.BaseMeetingResponse;
import KWU_LIKELION.MeetTime.dto.JoinRequest;
import KWU_LIKELION.MeetTime.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/meettime")
@Controller
@RequiredArgsConstructor
public class JoinController {
    private final JoinService joinService;
    @PostMapping("/join")
    public ResponseEntity<BaseMeetingResponse> joinMeeting(@RequestBody JoinRequest req){
        BaseMeetingResponse res=joinService.joinMeeting(req.getMeetingTitle(), req.getMeeintgId());

        if(res!=null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

}
