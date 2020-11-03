package com.example.TestProject.controller;

import com.example.TestProject.dto.Note;
import com.example.TestProject.dto.entity.NoteRequestBody;
import com.example.TestProject.service.INoteService;

import org.springframework.security.core.Authentication;
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
@RequestMapping(value = "/notes", produces = "application/json")
@AllArgsConstructor
public class NoteController {

    private final INoteService noteService;

    @GetMapping
    public List<Note> getNotes(Authentication authentication) {
        return noteService.getNotes(authentication.getName());
    }

    @GetMapping("/{id}")
    public Note getNoteById(
            @PathVariable Integer id,
            Authentication authentication
    ) throws Exception {
        return noteService.getNoteById(id, authentication.getName());
    }

    @PostMapping
    public Note createNote(Note note, Authentication authentication) {
        return noteService.createNote(note, authentication.getName());
    }

    @PutMapping
    public Note editNote(
            NoteRequestBody noteRequestBody,
            Authentication authentication
    ) throws Exception {
        return noteService.editNote(noteRequestBody, authentication.getName());
    }

    @DeleteMapping("/{id}")
    public Note removeNote(
            @PathVariable Integer id,
            Authentication authentication
    ) throws Exception {
        return noteService.removeNoteById(id, authentication.getName());
    }
}
