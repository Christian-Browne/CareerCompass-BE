package app.tracker.jobapplicatontracker.Service;

import app.tracker.jobapplicatontracker.Entity.Job;
import app.tracker.jobapplicatontracker.Entity.JobIntroInfo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface JobService {

    Job saveJob(Job job);

    Job getJob(Long id);
    List<JobIntroInfo> getAllJobs();

    void deleteJob(Long id);

    Job updateJob(Job job, Long id);
}
