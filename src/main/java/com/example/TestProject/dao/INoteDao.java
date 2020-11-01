package com.example.TestProject.dao;

import com.example.TestProject.dto.Note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INoteDao extends JpaRepository<Note, Integer> {
}
