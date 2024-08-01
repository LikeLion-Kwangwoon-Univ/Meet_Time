package com.example.demo.service.impl;

import com.example.demo.domain.Meeting;
import com.example.demo.domain.Users;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users saveUser(Users user, Meeting meeting) {
        validateUserInMeeting(meeting.getId(), user);

        user.setMeeting(meeting);
        return usersRepository.save(user);
    }

    public void validateUserInMeeting(Long meetingId, Users user){
        List<Users> usersInMeeting = usersRepository.findByMeetingId(meetingId);
        if(!usersInMeeting.isEmpty()){
            throw new IllegalStateException("회의에서 이미 사용중인 이름입니다.");
        }
    }
    @Override
    public Users getUserById(Long userId) {

        return usersRepository.findById(userId).orElse(null);
    }

    @Override
    public List<Users> getAllUsers(Long meetingId) {

        return usersRepository.findAll(meetingId);
    }
}
