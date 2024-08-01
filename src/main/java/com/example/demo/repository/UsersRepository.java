package com.example.demo.repository;

import com.example.demo.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findByMeetingId(Long meetingId);

    List<Users> findAll(Long meetingId);
}
