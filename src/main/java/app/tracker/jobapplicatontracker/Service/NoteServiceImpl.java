package app.tracker.jobapplicatontracker.Service;

import app.tracker.jobapplicatontracker.Entity.Job;
import app.tracker.jobapplicatontracker.Entity.Notes;
import app.tracker.jobapplicatontracker.Repository.JobRepository;
import app.tracker.jobapplicatontracker.Repository.NotesRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class NoteServiceImpl implements NotesService {

    NotesRepository notesRepository;
    JobRepository jobRepository;

    @Override
    public Notes saveNote(Long jobId, Notes note) {
        Job job = jobRepository.findById(jobId).get();
        note.setJob(job);
        return notesRepository.save(note);
    }

    @Override
    public List<Notes> getAllNotes(Long jobId) {
        Job job = jobRepository.findById(jobId).get();
        return (List<Notes>) job.getNotes();
    }
}
