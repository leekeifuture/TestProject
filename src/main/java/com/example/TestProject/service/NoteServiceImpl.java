package com.example.TestProject.service;

import com.example.TestProject.dao.INoteDao;
import com.example.TestProject.dto.Note;
import com.example.TestProject.dto.User;
import com.example.TestProject.dto.entity.NoteRequestBody;
import com.example.TestProject.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements INoteService {

    private final INoteDao noteDao;

    private final IUserService userService;

    @Override
    public List<Note> getNotes(String authenticationData) {
        User user = userService.getUserByAuthenticationData(authenticationData);

        return noteDao.findByAuthorId(user.getId());
    }

    @Override
    public Note getNoteById(Integer id, String authenticationData) {
        User user = userService.getUserByAuthenticationData(authenticationData);
        Optional<Note> result = noteDao.findByIdAndAuthorId(id, user.getId());

        if (result.isPresent()) {
            return result.get();
        }

        throw new ResourceNotFoundException("Not Found");
    }

    @Override
    public Note createNote(Note note, String authenticationData) {
        User user = userService.getUserByAuthenticationData(authenticationData);
        note.setAuthor(user);

        return noteDao.save(note);
    }

    @Override
    public Note editNote(NoteRequestBody noteRequestBody, String authenticationData) {
        User user = userService.getUserByAuthenticationData(authenticationData);

        if (noteDao.existsByIdAndAuthorId(noteRequestBody.getId(), user.getId())) {
            Note note = noteDao.findByIdAndAuthorId(
                    noteRequestBody.getId(),
                    user.getId()
            ).get();

            Note newNote = castToNote(noteRequestBody, note);

            return noteDao.save(newNote);
        }

        throw new ResourceNotFoundException("Not Found");
    }

    @Override
    public Note removeNoteById(Integer id, String authenticationData) {
        User user = userService.getUserByAuthenticationData(authenticationData);
        Optional<Note> note = noteDao.findByIdAndAuthorId(id, user.getId());

        if (!note.isPresent()) {
            throw new ResourceNotFoundException("Not Found");
        }

        noteDao.deleteById(id);
        return note.get();
    }

    @Override
    public NoteRequestBody castToNoteRequestBody(Note note) {
        return new NoteRequestBody(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getFields()
        );
    }

    @Override
    public Note castToNote(NoteRequestBody noteRequestBody, Note note) {
        note.setTitle(noteRequestBody.getTitle());
        note.setContent(noteRequestBody.getContent());
        note.setFields(noteRequestBody.getFields());

        return note;
    }

}
