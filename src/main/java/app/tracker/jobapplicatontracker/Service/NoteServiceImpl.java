package app.tracker.jobapplicatontracker.Service;

import Exceptions.CannotAddNoteException;
import Exceptions.JobNotFoundException;
import Exceptions.NoteNotFoundException;
import app.tracker.jobapplicatontracker.Entity.Job;
import app.tracker.jobapplicatontracker.Entity.Notes;
import app.tracker.jobapplicatontracker.Repository.JobRepository;
import app.tracker.jobapplicatontracker.Repository.NotesRepository;
import app.tracker.jobapplicatontracker.Security.User.User;
import app.tracker.jobapplicatontracker.Security.User.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NoteServiceImpl implements NotesService {

    private final NotesRepository notesRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    @Override
    public Notes saveNote(Long id, Notes note, Principal principal) {
        String email = principal.getName();
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) throw new UsernameNotFoundException("User doesn't exist");

        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            Job jobEntity = job.get();
            if (jobEntity.getUser().getEmail().equals(email)) {
                note.setJob(job.get());
            } else {
                throw new JobNotFoundException(id);
            }
        } else {
            throw new JobNotFoundException(id);
        }

        return notesRepository.save(note);
    }

    @Override
    public List<Notes> getAllNotes(Long id, Principal principal) {
        String email = principal.getName();
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) throw new UsernameNotFoundException("User doesn't exist");

        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            Job jobEntity = job.get();
            if (jobEntity.getUser().getEmail().equals(email)) {
                return job.get().getNotes();
            } else {
                throw new JobNotFoundException(id);
            }
        } else {
            throw new JobNotFoundException(id);
        }
    }

    @Override
    public Notes getNote(Long id, Long noteId, Principal principal) {
        String email = principal.getName();
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) throw new UsernameNotFoundException("User doesn't exist");

        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            Job jobEntity = job.get();
            if (jobEntity.getUser().getEmail().equals(email)) {

                Optional<Notes> note = notesRepository.findById(noteId);
                if (note.isPresent()) {
                    return note.get();
                } else {
                    throw new NoteNotFoundException(noteId);
                }

            } else {
                throw new JobNotFoundException(id);
            }
        } else {
            throw new JobNotFoundException(id);
        }

    }

    @Override
    public void deleteNote(Long id, Long noteId, Principal principal) {
        String email = principal.getName();
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) throw new UsernameNotFoundException("User doesn't exist");

        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            Job jobEntity = job.get();
            if (jobEntity.getUser().getEmail().equals(email)) {

                Optional<Notes> note = notesRepository.findById(noteId);
                if (note.isPresent()) {
                    notesRepository.deleteById(noteId);
                } else {
                    throw new NoteNotFoundException(noteId);
                }

            } else {
                throw new JobNotFoundException(id);
            }
        } else {
            throw new JobNotFoundException(id);
        }
    }

    @Override
    public Notes updateNote(Notes note, Long id, Long noteId, Principal principal) {
        String email = principal.getName();
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) throw new UsernameNotFoundException("User doesn't exist");

        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            Job jobEntity = job.get();
            if (jobEntity.getUser().getEmail().equals(email)) {

                Optional<Notes> prevNote = notesRepository.findById(noteId);
                if (prevNote.isPresent()) {
                    Notes unwrapped = prevNote.get();
                    unwrapped.setBody(note.getBody());
                    return notesRepository.save(unwrapped);
                } else {
                    throw new NoteNotFoundException(noteId);
                }

            } else {
                throw new JobNotFoundException(id);
            }
        } else {
            throw new JobNotFoundException(id);
        }



    }
}
