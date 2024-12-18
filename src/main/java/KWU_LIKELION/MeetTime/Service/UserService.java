package KWU_LIKELION.MeetTime.Service;

import KWU_LIKELION.MeetTime.Domain.Meeting;
import KWU_LIKELION.MeetTime.Domain.MeetingDay;
import KWU_LIKELION.MeetTime.Domain.Users;
import KWU_LIKELION.MeetTime.Repository.MeetingDayRepository;
import KWU_LIKELION.MeetTime.Repository.MeetingRepository;
import KWU_LIKELION.MeetTime.Repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final MeetingDayRepository meetingDayRepository;
    private final MeetingRepository meetingRepository;

    @Transactional
    public Long loginUser(Users user, Long meetingId){
        List<Users> meetingAllUsers = findAllUsersByMeetingId(meetingId);
        for(Users meetingUser : meetingAllUsers){
            if(meetingUser.getNickname().equals(user.getNickname())) {
                if(meetingUser.getPassword().equals(user.getPassword())){
                    return meetingUser.getId();
                }
            }
        }

        Optional<Meeting> findMeetingById = meetingRepository.findById(meetingId);
        Meeting meeting = findMeetingById.orElseThrow(() -> new IllegalStateException("존재하지 않는 MeetingID"));
        return createUser(user, meeting);
    }


    // 새로운 user 생성 (Possible Table 생성)
    @Transactional
    public Long createUser(Users user, Meeting meeting) {
        validateUserInMeeting(meeting.getId(), user);

        user.setMeeting(meeting);
        usersRepository.save(user);

        return user.getId();
    }

    // Meeting에서 중복된 이름을 가진 사람 확인
    public void validateUserInMeeting(Long meetingId, Users user){
        List<Users> usersInMeeting = usersRepository.findByMeetingId(meetingId);

        for(Users testUser : usersInMeeting){
            if(testUser.getNickname().equals(user.getNickname())){
                throw new IllegalStateException("회의에서 이미 사용중인 이름입니다.");
            }
        }
    }

    // 사용자 조회
    public Optional<Users> findUserById(Long userId){
        return usersRepository.findById(userId);
    }

    // meeting 모든 사용자
    public List<Users> findAllUsersByMeetingId(Long meetingId){
        return usersRepository.findByMeetingId(meetingId);
    }

    // user가 소속되어 있는 Meeting
    public Meeting findMeetingByUserId(Long userId) {
        Users findUser = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 userId"));

        return findUser.getMeeting();
    }

    // user가 소속되어 있는 MeetingDay
    public List<MeetingDay> findMeetingDayByUserId(Long userId){
        Meeting findMeeting = findMeetingByUserId(userId);

        return meetingDayRepository.findByMeetingId(findMeeting.getId());
    }
}
