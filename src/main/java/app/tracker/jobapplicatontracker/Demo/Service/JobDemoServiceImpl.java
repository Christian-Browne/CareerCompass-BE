package app.tracker.jobapplicatontracker.Demo.Service;

import app.tracker.jobapplicatontracker.Demo.Entity.JobDemo;
import app.tracker.jobapplicatontracker.Demo.Entity.JobDemoIntroInfo;
import app.tracker.jobapplicatontracker.Demo.Repository.JobDemoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class JobDemoServiceImpl implements JobDemoService {

    private final JobDemoRepository jobRepository;

    @Override
    public List<JobDemo> getAllJobs() {
        return jobRepository.findAllProjectedByOrderById();
    }
}
