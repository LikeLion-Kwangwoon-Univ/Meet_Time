package KWU_LIKELION.MeetTime.Repository;

import KWU_LIKELION.MeetTime.Domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findByMeetingId(Long meetingId);
}
