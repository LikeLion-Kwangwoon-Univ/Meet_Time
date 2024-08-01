package KWU_LIKELION.MeetTime.service;

import KWU_LIKELION.MeetTime.domain.Meeting;
import KWU_LIKELION.MeetTime.domain.MeetingDay;
import KWU_LIKELION.MeetTime.domain.PossibleTime;
import KWU_LIKELION.MeetTime.domain.Users;
import KWU_LIKELION.MeetTime.dto.UsersRequest;
import KWU_LIKELION.MeetTime.dto.UsersResponse;
import KWU_LIKELION.MeetTime.repository.MeetingRepository;
import KWU_LIKELION.MeetTime.repository.PossibleTimeRepository;
import KWU_LIKELION.MeetTime.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final MeetingRepository meetingRepository;
    private final PossibleTimeRepository possibleTimeRepository;
    //유저 생성 혹은 로그인
    public UsersResponse login(UsersRequest req)
    {
        String userName=req.getUserName();
        Meeting meeting=meetingRepository.findById(req.getMeetingId()).get();
        Optional<Users> optionalUsers=usersRepository.findByUserNameAndMeeting(userName,meeting);//존재하는 아이디인지 확인

        if(optionalUsers.isEmpty())//새로운 유저 생성
        {
            Users user=usersRepository.save(req.toEntity(meeting));
            return UsersResponse.fromEntity(req.getMeetingId(),user);
        }else{//password 확인 혹은 중복 아이디 확인
            Users user=optionalUsers.get();
            if(!user.getPassword().equals(req.getPassword())){
                return null;
            }else{
                return UsersResponse.fromEntity(req.getMeetingId(),user);
            }
        }

    }

    public List<String> getPossibleUserName(MeetingDay meetingDay, Integer possibleTime){
        return possibleTimeRepository.findAllUserNameByMeetingDayAndPossibletime(meetingDay,possibleTime);
    }

    public List<String> getAllUserName(Meeting meeting){
        return usersRepository.findAllUserNameByMeeting(meeting);
    }

}
