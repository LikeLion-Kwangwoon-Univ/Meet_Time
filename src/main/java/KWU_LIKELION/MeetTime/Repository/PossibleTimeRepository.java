package KWU_LIKELION.MeetTime.Repository;

import KWU_LIKELION.MeetTime.Domain.PossibleTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PossibleTimeRepository extends JpaRepository<PossibleTime, Long> {
    List<PossibleTime> findByUserId(Long userId);
    List<PossibleTime> findByMeetingDayId(Long meetingDayId);
}
