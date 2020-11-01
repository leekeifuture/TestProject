package com.example.TestProject.controller;

import com.example.TestProject.dto.Note;
import com.example.TestProject.service.INoteService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/notes", produces = "application/json")
@AllArgsConstructor
public class NotesController {

    private final INoteService noteService;

    @GetMapping
    public List<Note> getNotes() {
        return noteService.getNotes();
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable Integer id) throws Exception {
        return noteService.getNoteById(id);
    }

    @PostMapping
    public Note createNote(Note note) {
        return noteService.createNote(note);
    }

    @PutMapping
    public Note editNote(Note note) throws Exception {
        return noteService.editNote(note);
    }

    @DeleteMapping("/{id}")
    public void removeNote(@PathVariable Integer id) {
        noteService.removeNoteById(id);
    }
}
