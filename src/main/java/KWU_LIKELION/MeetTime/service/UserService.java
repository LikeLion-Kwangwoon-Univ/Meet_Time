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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final MeetingRepository meetingRepository;
    private final PossibleTimeRepository possibleTimeRepository;

    //유저 생성 혹은 로그인
    @Transactional
    public UsersResponse login(UsersRequest req)
    {
        //존재하는 유저인지 확인
        Optional<Users> optionalUsers=findUsers(req.getUserName(),req.getMeetingId());

        if(optionalUsers.isEmpty())//새로운 유저 생성
        {
            Users user=saveUser(req,req.getMeetingId());
            return UsersResponse.fromEntity(req.getMeetingId(),user);
        }else{//password 확인 혹은 중복 아이디 확인
            Users user=optionalUsers.get();
            if(!checkPassword(user, req.getPassword())){
                return null;
            }else{
                return UsersResponse.fromEntity(req.getMeetingId(),user);
            }
        }

    }

    //유저 생성
    @Transactional
    public Users saveUser(UsersRequest req,Long meetingId){
        Meeting meeting=meetingRepository.findById(meetingId).
                orElseThrow();
        return usersRepository.save(req.toEntity(meeting));
    }

    //존재하는 유저인지 확인
    public Optional<Users> findUsers(String userName,Long meetingId)
    {
        Meeting meeting=meetingRepository.findById(meetingId)
                .orElseThrow();
        return usersRepository.findByUserNameAndMeeting(userName,meeting);//존재하는 아이디인지 확인
    }

    //비밀번호 확인
    public Boolean checkPassword(Users user,String password){
        return user.getPassword().equals(password);
    }


    @Transactional(readOnly = true)
    public List<String> getPossibleUserName(MeetingDay meetingDay, Integer possibleTime){
        return possibleTimeRepository.findAllUserNameByMeetingDayAndPossibletime(meetingDay,possibleTime);
    }

    @Transactional(readOnly = true)
    public List<String> getAllUserName(Meeting meeting){
        return usersRepository.findAllUserNameByMeeting(meeting);
    }

}
