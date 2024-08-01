package KWU_LIKELION.MeetTime.controller;

import KWU_LIKELION.MeetTime.domain.Users;
import KWU_LIKELION.MeetTime.dto.UsersRequest;
import KWU_LIKELION.MeetTime.dto.UsersResponse;
import KWU_LIKELION.MeetTime.service.MeetingService;
import KWU_LIKELION.MeetTime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/meettime")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final MeetingService meetingService;

    //username과 password를 통해 user 확인 후 meeting 관련 데이터 전달
    @PostMapping(value = "/{meetingId}/login")
    public ResponseEntity<Users> login(@RequestBody UsersRequest req, @PathVariable Long meetingId){
        UsersResponse res=userService.login(req);//user 존재 확인
        if(res!=null) {
            return ResponseEntity.status(HttpStatus.CREATED).build();

        }else{//중복된 아이디 혹은 password 일치하지 않음
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
