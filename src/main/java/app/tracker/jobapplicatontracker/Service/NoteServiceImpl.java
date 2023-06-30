package app.tracker.jobapplicatontracker.Service;

import Exceptions.CannotAddNoteException;
import Exceptions.JobNotFoundException;
import Exceptions.NoteNotFoundException;
import app.tracker.jobapplicatontracker.Entity.Job;
import app.tracker.jobapplicatontracker.Entity.Notes;
import app.tracker.jobapplicatontracker.Repository.JobRepository;
import app.tracker.jobapplicatontracker.Repository.NotesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class NoteServiceImpl implements NotesService {

    NotesRepository notesRepository;
    JobRepository jobRepository;

    @Override
    public Notes saveNote(Long jobId, Notes note) {
        Optional<Job> job = jobRepository.findById(jobId);
        if (job.isPresent()) {
            note.setJob(job.get());
        } else {
            throw new CannotAddNoteException(jobId);
        }
        return notesRepository.save(note);
    }

    @Override
    public List<Notes> getAllNotes(Long jobId) {
        Optional<Job> job = jobRepository.findById(jobId);
        if (job.isPresent()) {
            return job.get().getNotes();
        } else {
            throw new JobNotFoundException(jobId);
        }
    }

    @Override
    public Notes getNote(Long jobId, Long noteId) {
        Optional<Job> job = jobRepository.findById(jobId);
        if (job.isEmpty()) throw new JobNotFoundException(jobId);

        Optional<Notes> note = notesRepository.findById(noteId);
        if (note.isPresent()) {
            return note.get();
        } else {
            throw new NoteNotFoundException(noteId);
        }
    }

    @Override
    public void deleteNote(Long jobId, Long noteId) {
        Optional<Job> job = jobRepository.findById(jobId);
        if (job.isEmpty()) {
            throw new JobNotFoundException(jobId);
        }

        Optional<Notes> note = notesRepository.findById(noteId);
        if (note.isPresent()) {
            notesRepository.deleteById(noteId);
        } else {
            throw new NoteNotFoundException(noteId);
        }
    }

    @Override
    public Notes updateNote(Notes note, Long jobId, Long noteId) {
        Optional<Job> job = jobRepository.findById(jobId);
        if (job.isEmpty()) throw new JobNotFoundException(jobId);

        Optional<Notes> prevNote = notesRepository.findById(noteId);

        if (prevNote.isPresent()) {
            Notes unwrapped = prevNote.get();
            unwrapped.setBody(note.getBody());
            return notesRepository.save(unwrapped);
        } else {
            throw new NoteNotFoundException(noteId);
        }

    }
}
