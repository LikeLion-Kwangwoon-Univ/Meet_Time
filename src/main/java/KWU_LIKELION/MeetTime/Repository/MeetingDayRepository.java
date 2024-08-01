package KWU_LIKELION.MeetTime.Repository;

import KWU_LIKELION.MeetTime.Domain.Meeting;
import KWU_LIKELION.MeetTime.Domain.MeetingDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MeetingDayRepository extends JpaRepository<MeetingDay, Long> {
    List<MeetingDay> findByMeetingId(Long meetingId);
}
