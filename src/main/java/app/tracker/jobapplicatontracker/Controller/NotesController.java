package app.tracker.jobapplicatontracker.Controller;

import app.tracker.jobapplicatontracker.Entity.Notes;
import app.tracker.jobapplicatontracker.Service.NoteServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/job")
public class NotesController {

    NoteServiceImpl notesServiceImpl;

    @PostMapping("/{jobId}/notes/add")
    public ResponseEntity<Notes> addNote(@RequestBody Notes note, @PathVariable Long jobId, Principal principal) {
        return new ResponseEntity<>(notesServiceImpl.saveNote(jobId, note, principal), HttpStatus.CREATED);
    }

    @GetMapping("/{jobId}/notes")
    public ResponseEntity<List<Notes>> getAllNotes(@PathVariable Long jobId, Principal principal) {
        return new ResponseEntity<>(notesServiceImpl.getAllNotes(jobId, principal), HttpStatus.OK);
    }

    @GetMapping("/{jobId}/notes/{id}")
    public ResponseEntity<Notes> getNote(@PathVariable Long jobId, @PathVariable Long id, Principal principal) {
        return new ResponseEntity<>(notesServiceImpl.getNote(jobId, id, principal), HttpStatus.OK);
    }

    @DeleteMapping("/{jobId}/notes/{noteId}/remove")
    public ResponseEntity<HttpStatus> deleteNote(@PathVariable Long jobId, @PathVariable Long noteId, Principal principal) {
        notesServiceImpl.deleteNote(jobId, noteId, principal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{jobId}/notes/{noteId}/update")
    public ResponseEntity<Notes> updateNote(@RequestBody Notes note, @PathVariable Long jobId, @PathVariable Long noteId, Principal principal) {
        return new ResponseEntity<>(notesServiceImpl.updateNote(note, jobId, noteId, principal), HttpStatus.CREATED);
    }

}
