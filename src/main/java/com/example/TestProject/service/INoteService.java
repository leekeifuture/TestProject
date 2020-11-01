package com.example.TestProject.service;

import com.example.TestProject.dto.Note;

import java.util.List;

public interface INoteService {

    List<Note> getNotes();

    Note getNoteById(Integer id) throws Exception;

    Note createNote(Note note);

    Note editNote(Note note) throws Exception;

    void removeNoteById(Integer id);
}
