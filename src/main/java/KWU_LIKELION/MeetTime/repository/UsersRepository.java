package KWU_LIKELION.MeetTime.repository;

import KWU_LIKELION.MeetTime.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByUserName(String userName);

    Boolean existsByUserName(String userName);
}
