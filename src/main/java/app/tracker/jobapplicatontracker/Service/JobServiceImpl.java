package app.tracker.jobapplicatontracker.Service;
import Exceptions.JobNotFoundException;
import app.tracker.jobapplicatontracker.Entity.Job;
import app.tracker.jobapplicatontracker.Entity.JobIntroInfo;
import app.tracker.jobapplicatontracker.Repository.JobRepository;
import app.tracker.jobapplicatontracker.Security.User.User;
import app.tracker.jobapplicatontracker.Security.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public Job saveJob(Job job, Principal principal) {
        String email = principal.getName();
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            job.setUser(user.get());
        } else {
            throw new UsernameNotFoundException("User doesn't exist");
        }
        return jobRepository.save(job);
    }

    @Override
    public Job getJob(Long id, Principal principal) {
        String email = principal.getName();
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) throw new UsernameNotFoundException("User doesn't exist");

        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            Job jobEntity = job.get();
            if (jobEntity.getUser().getEmail().equals(email)) {
                return jobEntity;
            } else {
                throw new JobNotFoundException(id);
            }
        } else {
            throw new JobNotFoundException(id);
        }
    }

    @Override
    public List<Job> getAllJobs(Principal principal) {
        String email = principal.getName();
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get().getJob();
        } else {
            throw new UsernameNotFoundException("User doesn't exist");
        }
//        return jobRepository.findAllProjectedByOrderById();
    }

    @Override
    public void deleteJob(Long id, Principal principal) {
        String email = principal.getName();
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) throw new UsernameNotFoundException("User doesn't exist");

        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            Job jobEntity = job.get();
            if (jobEntity.getUser().getEmail().equals(email)) {
                jobRepository.deleteById(id);
            } else {
                throw new JobNotFoundException(id);
            }
        } else {
            throw new JobNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public Job updateJob(Job job, Long id, Principal principal) {
        String email = principal.getName();
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) throw new UsernameNotFoundException("User doesn't exist");

        Optional<Job> prevJob = jobRepository.findById(id);
        if (prevJob.isPresent()) {
            Job jobEntity = prevJob.get();
            if (jobEntity.getUser().getEmail().equals(email)) {
                Job unwrapped = prevJob.get();
                unwrapped.setTitle(job.getTitle());
                unwrapped.setDate(job.getDate());
                unwrapped.setCompany(job.getCompany());
                unwrapped.setLogo(job.getLogo());
                unwrapped.setLocation(job.getLocation());
                unwrapped.setDescription(job.getDescription());
                unwrapped.setSalary(job.getSalary());
                unwrapped.setStatus(job.getStatus());
                unwrapped.setColor(job.getColor());
                return jobRepository.save(unwrapped);
            } else {
                throw new JobNotFoundException(id);
            }
        } else {
            throw new JobNotFoundException(id);
        }
    }
}
