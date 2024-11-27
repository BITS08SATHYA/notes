package dev.secure.notes.services;

import dev.secure.notes.models.Note;

import java.util.List;

public interface NoteService {
    Note createNoteForUser(String userName, String content);

    Note updateNoteForUser(Long noteId, String content, String userName);

    void deleteNoteForUser(Long noteId, String userName);

    List<Note> getAllNotesForUser(String userName);
}