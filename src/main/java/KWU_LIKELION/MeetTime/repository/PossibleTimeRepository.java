package KWU_LIKELION.MeetTime.repository;

import KWU_LIKELION.MeetTime.domain.MeetingDay;
import KWU_LIKELION.MeetTime.domain.PossibleTime;
import KWU_LIKELION.MeetTime.domain.Users;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;
import java.util.List;

public interface PossibleTimeRepository extends JpaRepository<PossibleTime,Long> {

    Boolean existsByUsersAndPossibleTimeAndMeetingDay(Users user, LocalTime localTime,MeetingDay meetingDay);

    List<PossibleTime> findALlByUsers(Users user);

    List<PossibleTime> findAllByMeetingDay(MeetingDay meetingDay);

    @Query("SELECT PT.possibleTime FROM PossibleTime PT WHERE PT.meetingDay=?1")
    List<LocalTime> findAllPossibleTimeByMeetingDay(MeetingDay meetingDay);
}
