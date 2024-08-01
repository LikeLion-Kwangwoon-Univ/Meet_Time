package KWU_LIKELION.MeetTime.repository;

import KWU_LIKELION.MeetTime.domain.Meeting;
import KWU_LIKELION.MeetTime.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByUserName(String userName);

    Optional<Users> findByUserNameAndMeeting(String userName,Meeting meeting);

    @Query("SELECT Users.userName FROM Users WHERE Users.meeting=?1")
    List<String> findAllUserNameByMeeting(Meeting meeting);
    Boolean existsByUserName(String userName);
}
