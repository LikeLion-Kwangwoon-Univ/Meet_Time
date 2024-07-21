package com.example.demo.service;

import com.example.demo.domain.MeetingDay;
import java.util.List;
public interface MeetingDayService {
    MeetingDay saveMeetingDay(MeetingDay meetingDay);
    //새로운 미팅 날짜를 저장
    MeetingDay updateMeetingDay(MeetingDay meetingDay);
    //기존 미팅 날짜 정보를 업데이트
    void deleteMeetingDay(Long id);
    //특정 미팅 날짜를 삭제
    MeetingDay getMeetingDayById(Long id);
    //특정 ID를 가진 미팅 날짜를 조회
    List<MeetingDay> getAllMeetingDays();
    //모든 미팅 날짜를 조회
}
