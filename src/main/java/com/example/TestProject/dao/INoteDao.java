package com.example.TestProject.dao;

import com.example.TestProject.dto.Note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface INoteDao extends JpaRepository<Note, Integer> {

    List<Note> findByAuthorId(Integer authorId);

    Optional<Note> findByIdAndAuthorId(Integer id, Integer authorId);

    Boolean existsByIdAndAuthorId(Integer id, Integer authorId);
}
