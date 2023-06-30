package app.tracker.jobapplicatontracker.Service;

import app.tracker.jobapplicatontracker.Entity.Notes;
import org.aspectj.weaver.ast.Not;

import java.util.List;

public interface NotesService {
    Notes saveNote(Long jobId, Notes notes);
    List<Notes> getAllNotes(Long jobId);

    Notes getNote(Long jobId, Long noteId);

    void deleteNote(Long jobId, Long noteId);

    Notes updateNote(Notes note, Long jobId, Long noteId);
}
