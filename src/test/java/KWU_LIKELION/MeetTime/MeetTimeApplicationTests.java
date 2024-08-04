package KWU_LIKELION.MeetTime;

import KWU_LIKELION.MeetTime.Domain.Meeting;
import KWU_LIKELION.MeetTime.Domain.MeetingDay;
import KWU_LIKELION.MeetTime.Domain.Users;
import KWU_LIKELION.MeetTime.Repository.MeetingRepository;
import KWU_LIKELION.MeetTime.Service.MeetingDayService;
import KWU_LIKELION.MeetTime.Service.MeetingService;
import KWU_LIKELION.MeetTime.Service.PossibleTimeService;
import KWU_LIKELION.MeetTime.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class MeetTimeApplicationTests {
	@Autowired private MeetingService meetingService;
	@Autowired private MeetingDayService meetingDayService;
    @Autowired
    private PossibleTimeService possibleTimeService;
    @Autowired
    private UserService userService;
    @Autowired
    private MeetingRepository meetingRepository;

	@Test
	@Transactional
	@Rollback(value = false)
	void contextLoads() {
		// Given
		/*Meeting meeting = new Meeting();
		meeting.setMeetingTitle("Team Meeting");
		meeting.setMeetingStartTime(10);
		meeting.setMeetingEndTime(20);

		LocalDate day1 = LocalDate.of(2024, 1, 1);
		LocalDate day2 = LocalDate.of(2024, 2, 1);
		LocalDate day3 = LocalDate.of(2024, 3, 14);

		List<LocalDate> days = List.of(day1, day2, day3);
		Long meetingId1 = meetingService.createDayMeeting(meeting, days);*/

		Users user = new Users();
		user.setNickname("강평종");
		user.setPassword("1234");
		Optional<Meeting> joinMeeting = meetingService.findMeetingById((long) 1);

		Users user2 = new Users();
		user2.setNickname("광운대");
		user2.setPassword("0000");
		userService.loginUser(user2, joinMeeting.get().getId());
		//userService.createUser(user2, joinMeeting.get());

		/*List<MeetingDay> meetingDay = meetingDayService.findAllMeetingById(meetingId1);

		List<Integer> pList = new ArrayList<>();
		pList.add(12);
		pList.add(14);
		pList.add(16);
		pList.add(18);
		possibleTimeService.possibleTimeAdd(user, meetingDay.get(0), pList);

		pList.add(10);
		possibleTimeService.possibleTimeAdd(user2, meetingDay.get(0), pList);

		List<Integer> countList = possibleTimeService.countUserMeetingDay(meetingDay.get(0).getId());
		List<Users> userList = possibleTimeService.allUsersInPossibleTimeNum(meetingDay.get(0).getId(), 12);
		for(Users u : userList){
			System.out.println(u.getNickname());
		}*/

		/*Meeting meeting2 = new Meeting();
		meeting2.setMeetingTitle("Team Meeting2");
		meeting2.setMeetingStartTime(10);
		meeting2.setMeetingEndTime(20);

		List<Week> weeks = List.of(Week.MON, Week.TUES, Week.SUN);

		// When
		Long meetingId2 = meetingService.createWeekMeeting(meeting2, weeks);

		meetingService.joinMeetingByCode("Team Meeting", meetingId1);

		List<MeetingDay> list = meetingDayService.findAllMeetingById(meetingId2);*/

	}

}
