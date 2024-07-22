package KWU_LIKELION.MeetTime.repository;

import KWU_LIKELION.MeetTime.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting,Long> {

}
