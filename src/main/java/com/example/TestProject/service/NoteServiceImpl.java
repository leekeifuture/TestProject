package com.example.TestProject.service;

import com.example.TestProject.dao.INoteDao;
import com.example.TestProject.dto.Note;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements INoteService {

    public final INoteDao noteDao;

    @Override
    public List<Note> getNotes() {
        return noteDao.findAll();
    }

    @Override
    public Note getNoteById(Integer id) throws Exception {
        Optional<Note> result = noteDao.findById(id);

        if (result.isPresent()) {
            return result.get();
        }

        throw new Exception("Note note found");
    }

    @Override
    public Note createNote(Note note) {
        return noteDao.save(note);
    }

    @Override
    public Note editNote(Note note) throws Exception {
        if (noteDao.existsById(note.getId())) {
            return noteDao.save(note);
        }

        throw new Exception("Note note found");
    }

    @Override
    public void removeNoteById(Integer id) {
        noteDao.deleteById(id);
    }
}
