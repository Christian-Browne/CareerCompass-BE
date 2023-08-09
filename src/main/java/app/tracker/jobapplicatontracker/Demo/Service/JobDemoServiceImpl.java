package app.tracker.jobapplicatontracker.Demo.Service;

import Exceptions.JobNotFoundException;
import app.tracker.jobapplicatontracker.Demo.Entity.JobDemo;
import app.tracker.jobapplicatontracker.Demo.Entity.JobDemoIntroInfo;
import app.tracker.jobapplicatontracker.Demo.Repository.JobDemoRepository;
import app.tracker.jobapplicatontracker.Entity.Job;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class JobDemoServiceImpl implements JobDemoService {

    private final JobDemoRepository jobRepository;

    @Override
    public List<JobDemo> getAllJobs() {
        return jobRepository.findAllProjectedByOrderById();
    }

    @Override
    public JobDemo getJob(Long id) {
        Optional<JobDemo> job = jobRepository.findById(id);
        if (job.isPresent()) {
            return job.get();
        } else {
            throw new JobNotFoundException(id);
        }
    }
}
