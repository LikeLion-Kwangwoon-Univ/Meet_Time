package com.example.demo.service;

import com.example.demo.domain.Meeting;
import com.example.demo.domain.Users;
import java.util.List;

public interface UsersService {
    Users saveUser(Users user, Meeting meeting);
    //새로운 유저를 저장
    void validateUserInMeeting(Long meetingId, Users user);
    //Meeting에서 중복된 이름을 가진 사람인지 확인
    Users getUserById(Long userId);
    //특정 유저를 조회
    List<Users> getAllUsers(Long meetingId);
    //미팅 내에 모든 유저를 조회
}
