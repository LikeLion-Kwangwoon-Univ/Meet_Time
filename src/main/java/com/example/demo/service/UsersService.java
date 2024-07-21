package com.example.demo.service;

import com.example.demo.domain.Users;
import java.util.List;

public interface UsersService {
    Users saveUser(Users user);
    //새로운 유저를 저장
    Users getUserById(Long id);
    //특정 ID를 가진 유저를 조회
    List<Users> getAllUsers();
    //모든 유저를 조회
}
