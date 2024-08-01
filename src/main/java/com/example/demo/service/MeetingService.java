package com.example.demo.service;

import com.example.demo.domain.Meeting;
import com.example.demo.domain.MeetingDay;
import java.util.List;
public interface MeetingService {

    Meeting createMeeting(String title, String type, List<String> dates, String startTime, String endTime);
    //새로운 모임 생성
    Meeting joinMeeting(Long meetingId, String title);
    //미팅 아이디를 통한 join
    void login(Long meetingId, String nickname, String password);
    //로그인 애매함.(user에 넣어주어야 하나?)
    Meeting selectMeeting(Long meetingId, Long userId);
    //미팅 정보 미팅아이디 이용해서 가져오기


}
