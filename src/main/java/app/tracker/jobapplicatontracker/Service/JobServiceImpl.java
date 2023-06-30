package app.tracker.jobapplicatontracker.Service;
import Exceptions.JobNotFoundException;
import app.tracker.jobapplicatontracker.Entity.Job;
import app.tracker.jobapplicatontracker.Entity.JobIntroInfo;
import app.tracker.jobapplicatontracker.Repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

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
        return jobRepository.findAllProjectedByOrderById();
    }

    @Override
    public void deleteJob(Long id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            jobRepository.deleteById(id);
        } else {
            throw new JobNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public Job updateJob(Job job, Long id) {
        Optional<Job> prevJob = jobRepository.findById(id);
        if (prevJob.isPresent()) {
            Job unwrapped = prevJob.get();
            unwrapped.setTitle(job.getTitle());
            unwrapped.setDate(job.getDate());
            unwrapped.setCompany(job.getCompany());
            unwrapped.setLogo(job.getLogo());
            unwrapped.setLocation(job.getLocation());
            unwrapped.setDescription(job.getDescription());
            unwrapped.setSalary(job.getSalary());
            unwrapped.setStatus(job.getStatus());
            return jobRepository.save(unwrapped);
        } else {
            throw new JobNotFoundException(id);
        }
    }
}
