package com.example.TestProject.service;

import com.example.TestProject.dto.Note;
import com.example.TestProject.dto.entity.NoteRequestBody;

import java.util.List;

public interface INoteService {

    List<Note> getNotes(String authenticationData);

    Note getNoteById(Integer id, String authenticationData) throws Exception;

    Note createNote(Note note, String authenticationData);

    Note editNote(NoteRequestBody noteRequestBody, String authenticationData) throws Exception;

    Note removeNoteById(Integer id, String authenticationData) throws Exception;

    NoteRequestBody castToNoteRequestBody(Note note);

    Note castToNote(NoteRequestBody noteRequestBody, Note note);
}
