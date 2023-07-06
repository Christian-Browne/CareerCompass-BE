package app.tracker.jobapplicatontracker.Service;

import app.tracker.jobapplicatontracker.Entity.Notes;
import org.aspectj.weaver.ast.Not;

import java.security.Principal;
import java.util.List;

public interface NotesService {
    Notes saveNote(Long jobId, Notes notes, Principal principal);
    List<Notes> getAllNotes(Long jobId, Principal principal);

    Notes getNote(Long jobId, Long noteId, Principal principal);

    void deleteNote(Long jobId, Long noteId, Principal principal);

    Notes updateNote(Notes note, Long jobId, Long noteId, Principal principal);
}
