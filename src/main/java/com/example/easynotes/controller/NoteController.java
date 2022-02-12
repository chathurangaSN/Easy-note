package com.example.easynotes.controller;

import com.example.easynotes.exception.ApiRequestException;
import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.repository.NoteRepository;
import com.example.easynotes.model.Note;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin()
@Slf4j
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    // Get All Notes
    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        log.info("in getAllNotes()");
        return noteRepository.findAll();
    }

    // Create a new Note
    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        String title = note.getTitle();
        Optional<Note> response = noteRepository.findByTitle(title);
        if (response.isPresent()) {
            throw new ApiRequestException("Title is taken");
        } else {
            return noteRepository.save(note);
        }
    }

    // Get a Single Note
    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    @GetMapping("/notes/title")

    public Note getNoteByTitle(
            @RequestParam(value = "title", required = false) String title) {
        return noteRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "title", title));
    }

    // Update a Note
    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(value = "id") Long noteId,
                           @Valid @RequestBody Note noteDetails) {

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        return noteRepository.save(note);
    }

    // Delete a Note
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }

}
