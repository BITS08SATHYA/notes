package dev.secure.notes.services.impl;

import dev.secure.notes.models.Note;
import dev.secure.notes.repositories.NoteRepository;
import dev.secure.notes.services.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class NoteServiceImpl  implements NoteService {

    private final NoteRepository noteRepository;

    @Override
    public Note createNoteForUser(String userName, String content){
        Note note = new Note();
        note.setContent(content);
        note.setOwnerUsername(userName);
        return noteRepository.save(note);
    }

    @Override
    public Note updateNoteForUser(Long noteId, String content, String userName){
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new RuntimeException("Note not found!"));
        note.setContent(content);
        Note updatedNote = noteRepository.save(note);
        return note;
    }

    @Override
    public void deleteNoteForUser(Long noteId, String userName){
        noteRepository.deleteById(noteId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Note> getAllNotesForUser(String userName) {
        return noteRepository.findByOwnerUsername(userName);
    }

}
