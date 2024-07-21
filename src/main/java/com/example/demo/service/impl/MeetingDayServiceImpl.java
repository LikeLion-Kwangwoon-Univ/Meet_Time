package com.example.demo.service.impl;

import com.example.demo.domain.MeetingDay;
import com.example.demo.repository.MeetingDayRepository;
import com.example.demo.service.MeetingDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingDayServiceImpl implements MeetingDayService {

    @Autowired
    private MeetingDayRepository meetingDayRepository;

    @Override
    public MeetingDay saveMeetingDay(MeetingDay meetingDay) {
        return meetingDayRepository.save(meetingDay);
    }

    @Override
    public MeetingDay updateMeetingDay(MeetingDay meetingDay) {
        return meetingDayRepository.save(meetingDay);
    }

    @Override
    public void deleteMeetingDay(Long id) {
        meetingDayRepository.deleteById(id);
    }

    @Override
    public MeetingDay getMeetingDayById(Long id) {
        return meetingDayRepository.findById(id).orElse(null);
    }

    @Override
    public List<MeetingDay> getAllMeetingDays() {
        return meetingDayRepository.findAll();
    }
}
