package KWU_LIKELION.MeetTime.Service;

import KWU_LIKELION.MeetTime.Domain.MeetingDay;
import KWU_LIKELION.MeetTime.Repository.MeetingDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MeetingDayService {
    private final MeetingDayRepository meetingDayRepository;

    // Meeting에 존재하는 모든 MeetingDay
    public List<MeetingDay> FindAllMeetingById(Long meetingId){
        return meetingDayRepository.findByMeetingId(meetingId);
    }

    // MeetingDayId 찾기
    public Optional<MeetingDay> FindMeetingDayById(Long meetingDayId){
        return meetingDayRepository.findById(meetingDayId);
    }
}
