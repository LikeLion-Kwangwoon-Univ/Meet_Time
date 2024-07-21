package com.example.demo.service;

import com.example.demo.domain.Meeting;
import java.util.List;
public interface MeetingService {
    Meeting saveMeeting(Meeting meeting);
    //새로운 미팅을 저장
    Meeting getMeetingById(Long id);
    //특정 ID를 가진 미팅 가져오기
    List<Meeting> getAllMeetings();
    //모든 미팅 가져오기
}
