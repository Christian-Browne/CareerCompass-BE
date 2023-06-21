package app.tracker.jobapplicatontracker.Service;

import app.tracker.jobapplicatontracker.Entity.Job;
import app.tracker.jobapplicatontracker.Entity.JobIntroInfo;

import java.util.List;

public interface JobService {

    Job saveJob(Job job);

    Job getJob(Long id);
    List<JobIntroInfo> getAllJobs();
}
