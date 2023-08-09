package app.tracker.jobapplicatontracker.Demo.Controller;

import app.tracker.jobapplicatontracker.Demo.Entity.JobDemo;
import app.tracker.jobapplicatontracker.Demo.Entity.JobDemoIntroInfo;
import app.tracker.jobapplicatontracker.Demo.Service.JobDemoServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class JobDemoController {

    JobDemoServiceImpl jobServiceImpl;

    @GetMapping("/demo")
    public ResponseEntity<List<JobDemo>> getAllJobs() {
        return new ResponseEntity<>(jobServiceImpl.getAllJobs(), HttpStatus.OK);
    }

    @GetMapping("/demo/job/{id}")
    public ResponseEntity<JobDemo> getJob(@PathVariable Long id) {
        return new ResponseEntity<>(jobServiceImpl.getJob(id), HttpStatus.OK);
    }
}