package KWU_LIKELION.MeetTime.service;

import KWU_LIKELION.MeetTime.domain.Meeting;
import KWU_LIKELION.MeetTime.domain.MeetingDay;
import KWU_LIKELION.MeetTime.domain.PossibleTime;
import KWU_LIKELION.MeetTime.domain.Users;
import KWU_LIKELION.MeetTime.dto.UsersRequest;
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
    public Users login(UsersRequest req)
    {
        String userName=req.getUserName();
        Optional<Users> optionalUsers=usersRepository.findByUserName(userName);//존재하는 아이디인지 확인

        Users user;
        if(optionalUsers.isEmpty())//새로운 유저 생성
        {
            Meeting meeting=meetingRepository.findById(req.getMeetingId()).get();
            user=usersRepository.save(req.toEntity(meeting));
        }else{//password 확인 혹은 중복 아이디 확인
            user=optionalUsers.get();
            if(!user.getPassword().equals(req.getPassword())){
                return null;
            }
        }

        return user;
    }

    public List<String> getPossibleUserName(MeetingDay meetingDay, Integer possibleTime){
        return possibleTimeRepository.findAllUserNameByMeetingDayAndPossibletime(meetingDay,possibleTime);
    }

    public List<String> getAllUserName(Meeting meeting){
        return usersRepository.findAllUserNameByMeeting(meeting);
    }

}
