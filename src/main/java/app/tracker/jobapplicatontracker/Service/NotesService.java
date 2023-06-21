package app.tracker.jobapplicatontracker.Service;

import app.tracker.jobapplicatontracker.Entity.Notes;

import java.util.List;

public interface NotesService {
    Notes saveNote(Long jobId, Notes notes);
    List<Notes> getAllNotes(Long jobId);
}
