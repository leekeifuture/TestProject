package com.example.TestProject.service;

import com.example.TestProject.dao.IUserDao;
import com.example.TestProject.dto.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserDao userDao;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void signUp(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }
}
