package com.example.TestProject.dao;

import com.example.TestProject.dto.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
