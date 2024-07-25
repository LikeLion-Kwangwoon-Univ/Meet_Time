package KWU_LIKELION.MeetTime.Repository;

import KWU_LIKELION.MeetTime.Domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

}
