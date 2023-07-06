package app.tracker.jobapplicatontracker.Service;

import app.tracker.jobapplicatontracker.Entity.Job;
import app.tracker.jobapplicatontracker.Entity.JobIntroInfo;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

public interface JobService {

    Job saveJob(Job job, Principal principal);

    Job getJob(Long id, Principal principal);
    List<Job> getAllJobs(Principal principal);

    void deleteJob(Long id, Principal principal);

    Job updateJob(Job job, Long id, Principal principal);
}
