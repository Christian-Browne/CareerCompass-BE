package app.tracker.jobapplicatontracker.Service;
import Exceptions.JobNotFoundException;
import app.tracker.jobapplicatontracker.Entity.Job;
import app.tracker.jobapplicatontracker.Entity.JobIntroInfo;
import app.tracker.jobapplicatontracker.Repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class JobServiceImpl implements JobService {

    JobRepository jobRepository;

    @Override
    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job getJob(Long id) {
       Optional<Job> job = jobRepository.findById(id);
       if (job.isPresent()) {
           return job.get();
       } else {
           throw new JobNotFoundException(id);
       }
    }


    @Override
    public List<JobIntroInfo> getAllJobs() {
        return (List<JobIntroInfo>) jobRepository.findAllProjectedBy();
    }
}
