package KWU_LIKELION.MeetTime.Service;

import KWU_LIKELION.MeetTime.Domain.Enum.Week;
import KWU_LIKELION.MeetTime.Domain.Meeting;
import KWU_LIKELION.MeetTime.Domain.MeetingDay;
import KWU_LIKELION.MeetTime.Domain.Users;
import KWU_LIKELION.MeetTime.Repository.MeetingDayRepository;
import KWU_LIKELION.MeetTime.Repository.MeetingRepository;
import KWU_LIKELION.MeetTime.Repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static KWU_LIKELION.MeetTime.Domain.Meeting.newDayMeeting;
import static KWU_LIKELION.MeetTime.Domain.Meeting.newWeekMeeting;
import static KWU_LIKELION.MeetTime.Domain.MeetingDay.newDayMeetingDay;
import static KWU_LIKELION.MeetTime.Domain.MeetingDay.newWeekMeetingDay;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final MeetingDayRepository meetingDayRepository;

    @Transactional
    // Meeting과 날짜를 받아서 생성
    public Long createDayMeeting(Meeting meeting, List<LocalDate> days){
        Meeting newMeeting = newDayMeeting(meeting.getMeetingTitle(),
                meeting.getMeetingStartTime(), meeting.getMeetingEndTime());
        meetingRepository.save(newMeeting);

        for(LocalDate day : days){
            MeetingDay newMeetingDay = newDayMeetingDay(day, newMeeting);
            meetingDayRepository.save(newMeetingDay);
        }

        return newMeeting.getId();
    }

    @Transactional
    // Meeting과 요일을 받아서 생성
    public Long createWeekMeeting(Meeting meeting, List<Week> weeks){
        Meeting newMeeting = newWeekMeeting(meeting.getMeetingTitle(),
                meeting.getMeetingStartTime(), meeting.getMeetingEndTime());
        meetingRepository.save(newMeeting);

        for(Week week : weeks){
            MeetingDay newMeetingDay = newWeekMeetingDay(week, newMeeting);
            meetingDayRepository.save(newMeetingDay);
        }

        return newMeeting.getId();
    }

    // Meeting 찾기
    public Optional<Meeting> FindMeetingById(Long meetingId){
        return meetingRepository.findById(meetingId);
    }
}
