package KWU_LIKELION.MeetTime.repository;

import KWU_LIKELION.MeetTime.domain.Meeting;
import KWU_LIKELION.MeetTime.domain.MeetingDay;
import KWU_LIKELION.MeetTime.domain.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MeetingDayRepository extends JpaRepository<MeetingDay,Long> {

    List<MeetingDay> findAllByMeeting(Meeting meeting);

    @Query("SELECT MD FROM MeetingDay MD WHERE MD.Meeting=?1 AND MD.User=?2")
    List<MeetingDay> findAllByMeetingAndUser(Meeting meeting, Users user);
    @Query("SELECT MD.day FROM MeetingDay MD WHERE MD.meeting=?1")
    List<Long> findAllMeetingDayIdByMeeting(Meeting meeting);
}