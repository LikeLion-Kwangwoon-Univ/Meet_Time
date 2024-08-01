package com.example.demo.service.impl;

import com.example.demo.domain.MeetingDay;
import com.example.demo.repository.MeetingDayRepository;
import com.example.demo.service.MeetingDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MeetingDayServiceImpl implements MeetingDayService {

    @Autowired
    private MeetingDayRepository meetingDayRepository;

    @Override
    public MeetingDay saveMeetingDay(MeetingDay meetingDay) {
        return meetingDayRepository.save(meetingDay);
    }
    //id가 null일때 save 메서드는 insert sql문을 실행
    @Override
    public MeetingDay getMeetingDayById(Long meetingDayId) {

        return meetingDayRepository.findById(meetingDayId).orElse(null);
    }

    @Override
    public List<MeetingDay> getAllMeetingDays(Long meetingId) {

        return meetingDayRepository.findAll(meetingId);
    }
}
