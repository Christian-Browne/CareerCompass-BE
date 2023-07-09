package app.tracker.jobapplicatontracker.Demo.Service;

import app.tracker.jobapplicatontracker.Demo.Entity.JobDemo;
import app.tracker.jobapplicatontracker.Demo.Entity.NotesDemo;
import app.tracker.jobapplicatontracker.Demo.Repository.JobDemoRepository;
import app.tracker.jobapplicatontracker.Demo.Repository.NotesDemoRepository;
import Exceptions.CannotAddNoteException;
import Exceptions.JobNotFoundException;
import Exceptions.NoteNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class NotesDemoServiceImpl implements NotesDemoService {

    NotesDemoRepository notesRepository;
    JobDemoRepository jobRepository;

    @Override
    public NotesDemo saveNote(Long jobId, NotesDemo note) {
        Optional<JobDemo> job = jobRepository.findById(jobId);
        if (job.isPresent()) {
            note.setJob(job.get());
        } else {
            throw new CannotAddNoteException(jobId);
        }
        return notesRepository.save(note);
    }

    @Override
    public List<NotesDemo> getAllNotes(Long jobId) {
        Optional<JobDemo> job = jobRepository.findById(jobId);
        if (job.isPresent()) {
            return job.get().getNotes();
        } else {
            throw new JobNotFoundException(jobId);
        }
    }

    @Override
    public NotesDemo getNote(Long jobId, Long noteId) {
        Optional<JobDemo> job = jobRepository.findById(jobId);
        if (job.isEmpty()) throw new JobNotFoundException(jobId);

        Optional<NotesDemo> note = notesRepository.findById(noteId);
        if (note.isPresent()) {
            return note.get();
        } else {
            throw new NoteNotFoundException(noteId);
        }
    }

    @Override
    public void deleteNote(Long jobId, Long noteId) {
        Optional<JobDemo> job = jobRepository.findById(jobId);
        if (job.isEmpty()) {
            throw new JobNotFoundException(jobId);
        }

        Optional<NotesDemo> note = notesRepository.findById(noteId);
        if (note.isPresent()) {
            notesRepository.deleteById(noteId);
        } else {
            throw new NoteNotFoundException(noteId);
        }
    }

    @Override
    public NotesDemo updateNote(NotesDemo note, Long jobId, Long noteId) {
        Optional<JobDemo> job = jobRepository.findById(jobId);
        if (job.isEmpty()) throw new JobNotFoundException(jobId);

        Optional<NotesDemo> prevNote = notesRepository.findById(noteId);

        if (prevNote.isPresent()) {
            NotesDemo unwrapped = prevNote.get();
            unwrapped.setBody(note.getBody());
            return notesRepository.save(unwrapped);
        } else {
            throw new NoteNotFoundException(noteId);
        }

    }
}