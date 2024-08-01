package KWU_LIKELION.MeetTime;

import KWU_LIKELION.MeetTime.Domain.Enum.Week;
import KWU_LIKELION.MeetTime.Domain.Meeting;
import KWU_LIKELION.MeetTime.Domain.MeetingDay;
import KWU_LIKELION.MeetTime.Service.MeetingDayService;
import KWU_LIKELION.MeetTime.Service.MeetingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@SpringBootTest
class MeetTimeApplicationTests {
	@Autowired private MeetingService meetingService;
	@Autowired private MeetingDayService meetingDayService;

	@Test
	@Transactional
	@Rollback(value = false)
	void contextLoads() {
		// Given
		Meeting meeting = new Meeting();
		meeting.setMeetingTitle("Team Meeting");
		meeting.setMeetingStartTime(LocalTime.of(8, 00));
		meeting.setMeetingEndTime(LocalTime.of(12,00));

		LocalDate day1 = LocalDate.of(2024, 1, 1);
		LocalDate day2 = LocalDate.of(2024, 2, 1);
		LocalDate day3 = LocalDate.of(2024, 3, 14);

		List<LocalDate> days = List.of(day1, day2, day3);
		Long meetingId1 = meetingService.createDayMeeting(meeting, days);


		Meeting meeting2 = new Meeting();
		meeting2.setMeetingTitle("Team Meeting2");
		meeting2.setMeetingStartTime(LocalTime.of(8, 00));
		meeting2.setMeetingEndTime(LocalTime.of(12,00));

		List<Week> weeks = List.of(Week.MON, Week.TUES, Week.SUN);

		// When
		Long meetingId2 = meetingService.createWeekMeeting(meeting2, weeks);

		meetingService.joinMeetingByCode("Team Meeting", meetingId1);

		List<MeetingDay> list = meetingDayService.findAllMeetingById(meetingId2);

		System.out.println(meeting.meetingSizePossibleTime());
	}

}
