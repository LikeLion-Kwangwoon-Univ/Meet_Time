package com.example.demo.service.impl;

import com.example.demo.domain.Meeting;
import com.example.demo.domain.MeetingDay;
import com.example.demo.domain.Users;
import com.example.demo.domain.enums.MeetingType;
import com.example.demo.repository.MeetingDayRepository;
import com.example.demo.repository.MeetingRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private MeetingDayRepository meetingDayRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Meeting createMeeting(String title, String type, List<String> dates, String startTime, String endTime) {
        Meeting meeting = new Meeting();
        meeting.setMeetingTitle(title);
        meeting.setMeetingType(MeetingType.valueOf(type));
        meeting.setMeetingStartTime(LocalTime.parse(startTime));
        meeting.setMeetingEndTime(LocalTime.parse(endTime));
        meeting.setMeetingCreateTime(LocalDateTime.now());

        List<MeetingDay> meetingDays = new ArrayList<>();
        for (String date : dates) {
            MeetingDay meetingDay = new MeetingDay();
            meetingDay.setMeetingday(LocalDate.parse(date));
            meetingDay.setMeeting(meeting);
            meetingDays.add(meetingDay);
        }
        meeting.setMeetingDays(meetingDays);
        meeting = meetingRepository.save(meeting);

        meetingDayRepository.saveAll(meetingDays);
        return meeting;
    }

    @Override
    public Meeting joinMeeting(Long meetingId, String title) {
        return meetingRepository.findById(meetingId).orElse(null);
    }

    @Override
    public void login(Long meetingId, String nickname, String password) {
        Meeting meeting = meetingRepository.findById(meetingId).orElse(null);
        if (meeting != null) {
            Users user = new Users();
            user.setNickname(nickname);
            user.setPassword(password);
            user.setMeeting(meeting);
            usersRepository.save(user);
        }
    }

    @Override
    public Meeting selectMeeting(Long meetingId, Long userId) {
        return meetingRepository.findById(meetingId).orElse(null);
    }


}