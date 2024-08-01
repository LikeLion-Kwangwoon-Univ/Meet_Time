package KWU_LIKELION.MeetTime.service;

import KWU_LIKELION.MeetTime.domain.Meeting;
import KWU_LIKELION.MeetTime.domain.MeetingDay;
import KWU_LIKELION.MeetTime.domain.MeetingType;
import KWU_LIKELION.MeetTime.domain.MeetingWeek;
import KWU_LIKELION.MeetTime.repository.MeetingDayRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingDayService {
    private final MeetingDayRepository meetingDayRepository;

    //meetingList 반환 -> type에 따라 day 혹은 week
    public List<?> getMeetingList(MeetingType meetingType, List<MeetingDay> meetingDayList){

        if(meetingType== MeetingType.DAY){//meetingType이 day라면 day 리스트 반환
            List<LocalDate> meetingList=meetingDayList.stream()
                    .map(meetingDay -> meetingDay.getDay())
                    .collect(Collectors.toList());
            return meetingList;
        }else{//meetingType이 week라면 week 리스트 반환
            List<MeetingWeek> meetingList=meetingDayList.stream()
                    .map(meetingDay -> meetingDay.getWeek())
                    .collect(Collectors.toList());
            return meetingList;
        }

    }
    public List<Long> getMeetingDayIdList(Meeting meeting){
        return meetingDayRepository.findAllMeetingDayIdByMeeting(meeting);
    }
}
