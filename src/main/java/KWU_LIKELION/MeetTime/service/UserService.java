package KWU_LIKELION.MeetTime.service;

import KWU_LIKELION.MeetTime.domain.Users;
import KWU_LIKELION.MeetTime.dto.UsersRequest;
import KWU_LIKELION.MeetTime.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;

    //유저 생성 혹은 로그인
    public Users login(UsersRequest req)
    {
        String userName=req.getUserName();
        Optional<Users> optionalUsers=usersRepository.findByUserName(userName);//존재하는 아이디인지 확인

        Users user;
        if(optionalUsers.isEmpty())//새로운 유저 생성
        {
            user=usersRepository.save(req.toEntity());
        }else{//password 확인 혹은 중복 아이디 확인
            user=optionalUsers.get();
            if(!user.getPassword().equals(req.getPassword())){
                return null;
            }
        }

        return user;
    }

}
