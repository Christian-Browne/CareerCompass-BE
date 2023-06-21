package app.tracker.jobapplicatontracker.Controller;

import app.tracker.jobapplicatontracker.Entity.Notes;
import app.tracker.jobapplicatontracker.Service.NoteServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class NotesController {

    NoteServiceImpl notesServiceImpl;

    @PostMapping("/job/{jobId}/notes/add")
    public ResponseEntity<Notes> addNote(@RequestBody Notes note, @PathVariable Long jobId) {
        return new ResponseEntity<>(notesServiceImpl.saveNote(jobId, note), HttpStatus.CREATED);
    }

    @GetMapping("/job/{jobId}/notes")
    public ResponseEntity<List<Notes>> getAllNotes(@PathVariable Long jobId) {
        return new ResponseEntity<>(notesServiceImpl.getAllNotes(jobId), HttpStatus.OK);
    }

}
