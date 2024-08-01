package KWU_LIKELION.MeetTime.controller;

import KWU_LIKELION.MeetTime.domain.Users;
import KWU_LIKELION.MeetTime.dto.UsersRequest;
import KWU_LIKELION.MeetTime.service.MeetingService;
import KWU_LIKELION.MeetTime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final MeetingService meetingService;

    //username과 password를 통해 user 확인 후 meeting 관련 데이터 전달
    @PostMapping(value = "/{boardId}/login")
    public ResponseEntity<MeetingByUserResponse> login(@RequestBody UsersRequest req){
        Users user=userService.login(req);//user 존재 확인

        if(user!=null) {
            //user에 대한 meeting 정보
            MeetingByUserResponse meetingByUserResponse =meetingService.getMeetingByUser(user);
            if(meetingByUserResponse !=null){
                return ResponseEntity.ok(meetingByUserResponse);
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

        }else{//중복된 아이디 혹은 password 일치하지 않음
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
