package KWU_LIKELION.MeetTime.repository;

import KWU_LIKELION.MeetTime.domain.Meeting;
import KWU_LIKELION.MeetTime.domain.MeetingDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingDayRepository extends JpaRepository<MeetingDay,Long> {

    List<MeetingDay> findAllByMeeting(Meeting meeting);
}
