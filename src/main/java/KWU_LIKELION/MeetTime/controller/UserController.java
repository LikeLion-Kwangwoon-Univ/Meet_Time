package KWU_LIKELION.MeetTime.controller;

import KWU_LIKELION.MeetTime.dto.UsersRequest;
import KWU_LIKELION.MeetTime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //meetingId와 함께 userName,password 전달
    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody UsersRequest req){
        if(userService.login(req)!=null) {
            return ResponseEntity.ok("로그인완료");//반환타입 변경 필요
        }else{//중복된 아이디 혹은 password 일치하지 않음
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
