package KWU_LIKELION.MeetTime.repository;

import KWU_LIKELION.MeetTime.domain.PossibleTime;
import KWU_LIKELION.MeetTime.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;

public interface PossibleTimeRepository extends JpaRepository<PossibleTime,Long> {
    Boolean existsByUsersAndPossibleTime(Users user, LocalTime localTime);
}
