package app.tracker.jobapplicatontracker.Demo.Service;

import app.tracker.jobapplicatontracker.Demo.Entity.NotesDemo;

import java.util.List;

public interface NotesDemoService {
    NotesDemo saveNote(Long jobId, NotesDemo notes);
    List<NotesDemo> getAllNotes(Long jobId);

    NotesDemo getNote(Long jobId, Long noteId);

    void deleteNote(Long jobId, Long noteId);

    NotesDemo updateNote(NotesDemo note, Long jobId, Long noteId);
}
