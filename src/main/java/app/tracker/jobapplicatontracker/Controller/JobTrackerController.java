package app.tracker.jobapplicatontracker.Controller;

import app.tracker.jobapplicatontracker.Entity.Job;
import app.tracker.jobapplicatontracker.Entity.JobIntroInfo;
import app.tracker.jobapplicatontracker.Service.JobServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class JobTrackerController {

    private final JobServiceImpl jobServiceImpl;

    @GetMapping("/home")
    public ResponseEntity<List<Job>> getAllJobs(Principal principal) {
        return new ResponseEntity<>(jobServiceImpl.getAllJobs(principal), HttpStatus.OK);
    }

    @PostMapping("/job/add")
    public ResponseEntity<Job> addJob(@RequestBody @Valid Job job, Principal principal) {
        return new ResponseEntity<>(jobServiceImpl.saveJob(job, principal), HttpStatus.CREATED);
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<Job> getJob(@PathVariable Long id, Principal principal) {
        return new ResponseEntity<>(jobServiceImpl.getJob(id, principal), HttpStatus.OK);
    }

    @DeleteMapping("job/{id}/delete")
    public ResponseEntity<HttpStatus> deleteJob(@PathVariable Long id, Principal principal) {
        jobServiceImpl.deleteJob(id, principal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("job/{id}/update")
    public ResponseEntity<Job> updateJob(@RequestBody Job job, @PathVariable Long id, Principal principal) {
        return new ResponseEntity<>(jobServiceImpl.updateJob(job, id, principal), HttpStatus.CREATED);
    }
}
