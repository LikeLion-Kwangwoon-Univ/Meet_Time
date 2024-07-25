package KWU_LIKELION.MeetTime.Service;

import KWU_LIKELION.MeetTime.Domain.MeetingDay;
import KWU_LIKELION.MeetTime.Domain.PossibleTime;
import KWU_LIKELION.MeetTime.Domain.Users;
import KWU_LIKELION.MeetTime.Repository.PossibleTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

import static KWU_LIKELION.MeetTime.Domain.PossibleTime.newPossibleTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PossibleTimeService {
    private final PossibleTimeRepository possibleTimeRepository;

    public Long PossibleTimeInMeeting(Users user, MeetingDay meetingDay, LocalTime localTime){
        PossibleTime possibleTime = newPossibleTime(localTime);
        possibleTime.setUser(user);
        possibleTime.setMeetingDay(meetingDay);

        return possibleTime.getId();
    }
}
