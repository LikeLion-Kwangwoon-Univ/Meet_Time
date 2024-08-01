package com.example.demo.service;

import com.example.demo.domain.MeetingDay;
import java.util.List;
public interface MeetingDayService {
    MeetingDay saveMeetingDay(MeetingDay meetingDay);
    //새로운 미팅 날짜를 저장
    MeetingDay getMeetingDayById(Long meetingDayId);
    //특정 ID를 가진 미팅 날짜를 조회
    List<MeetingDay> getAllMeetingDays(Long meetingId);
    //미팅에 존재하는 모든 날짜를 조회
}
