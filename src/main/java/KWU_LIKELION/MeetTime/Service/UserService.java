package KWU_LIKELION.MeetTime.Service;

import KWU_LIKELION.MeetTime.Domain.Meeting;
import KWU_LIKELION.MeetTime.Domain.Users;
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

    // 새로운 user 생성
    public Long createUser(Users user, Meeting meeting) {
        validateUserInMeeting(meeting.getId(), user);

        user.setMeeting(meeting);
        usersRepository.save(user);

        return user.getId();
    }

    // Meeting에서 중복된 이름을 가진 사람 확인
    public void validateUserInMeeting(Long meetingId, Users user){
        List<Users> usersInMeeting = usersRepository.findByMeetingId(meetingId);
        if(!usersInMeeting.isEmpty()){
            throw new IllegalStateException("회의에서 이미 사용중인 이름입니다.");
        }
    }

    // 사용자 조회
    public Optional<Users> FindUserById(Long userId){
        return usersRepository.findById(userId);
    }

    // meeting 모든 사용자
    public List<Users> FindUsersByMeetingId(Long meetingId){
        return usersRepository.findByMeetingId(meetingId);
    }
}
