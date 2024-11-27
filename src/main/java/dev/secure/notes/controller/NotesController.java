package dev.secure.notes.controller;


import dev.secure.notes.models.Note;
import dev.secure.notes.services.NoteService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@Getter@Setter@AllArgsConstructor
@Slf4j
public class NotesController {

    private final NoteService noteService;

    @PostMapping
    public Note createNote(@RequestBody String content, @AuthenticationPrincipal UserDetails userDetails ) {
        String userName = userDetails.getUsername();
        log.info("USER_DETAILS: " + userName);
        return noteService.createNoteForUser(userName, content);
    }

    @GetMapping
    public List<Note> getUserNotes(@AuthenticationPrincipal UserDetails userDetails) {
        String userName = userDetails.getUsername();
        log.info("USER_DETAILS: " + userName);
        return noteService.getAllNotesForUser(userName);
    }

    @PutMapping("/{noteId}")
    public Note updateNote(@PathVariable Long noteId, @RequestBody String content,
                           @AuthenticationPrincipal UserDetails userDetails) {
        String userName = userDetails.getUsername();
        noteService.updateNoteForUser(noteId, content, userName);
        return noteService.updateNoteForUser(noteId, content, userName);
    }

    @DeleteMapping("/{noteId}")
    public void deleteNote(@PathVariable Long noteId, @AuthenticationPrincipal UserDetails userDetails) {
        String userName = userDetails.getUsername();
        noteService.deleteNoteForUser(noteId, userName);
    }

}
