package app.tracker.jobapplicatontracker.Controller;

import app.tracker.jobapplicatontracker.Entity.Job;
import app.tracker.jobapplicatontracker.Entity.JobIntroInfo;
import app.tracker.jobapplicatontracker.Service.JobServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class JobTrackerController {

    JobServiceImpl jobServiceImpl;

    @GetMapping("/")
    public ResponseEntity<List<JobIntroInfo>> getAllJobs() {
        return new ResponseEntity<>(jobServiceImpl.getAllJobs(), HttpStatus.OK);
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<Job> getJob(@PathVariable Long id) {
        return new ResponseEntity<>(jobServiceImpl.getJob(id), HttpStatus.OK);
    }

    @PostMapping("/job/add")
    public ResponseEntity<Job> addJob(@RequestBody @Valid Job job) {
        return new ResponseEntity<>(jobServiceImpl.saveJob(job), HttpStatus.CREATED);
    }
}
