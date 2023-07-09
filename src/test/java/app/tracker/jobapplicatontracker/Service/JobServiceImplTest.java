package app.tracker.jobapplicatontracker.Service;

import app.tracker.jobapplicatontracker.Entity.Job;
import app.tracker.jobapplicatontracker.Repository.JobRepository;
import app.tracker.jobapplicatontracker.Security.User.User;
import app.tracker.jobapplicatontracker.Security.User.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobServiceImplTest {

    @Mock
    private JobRepository jobRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private Principal principal;

    @InjectMocks
    private JobServiceImpl jobService;

    @Test
    public void saveJobTest_userExists() {
        Job job = new Job();
        User user = new User();
        user.setEmail("test@example.com");

        when(principal.getName()).thenReturn("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(jobRepository.save(any(Job.class))).thenReturn(job);

        Job savedJob = jobService.saveJob(job, principal);

        assertEquals(job, savedJob);
        verify(jobRepository, times(1)).save(job);
    }

    @Test
    public void saveJobTest_userDoesNotExist() {
        Job job = new Job();
        User user = new User();

        when(principal.getName()).thenReturn("wrong@example.com");
        when(userRepository.findByEmail("wrong@example.com")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            jobService.saveJob(job, principal);
        });
    }

    @Test
    public void getJobTest_jobExist() {
        Job job = new Job();
        User user = new User();
        // we are setting what the returned job object should be
        job.setUser(user);
        user.setEmail("test@example.com");

        when(principal.getName()).thenReturn("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(jobRepository.findById(1L)).thenReturn(Optional.of(job));

        Job retrievedJob = jobService.getJob(1L, principal);

        assertEquals(job, retrievedJob);
    }

    @Test
    public void getAllJobsTest_userExist() {
        Job job1 = new Job();
        Job job2 = new Job();
        List<Job> jobs = Arrays.asList(job1, job2);

        User user = new User();
        user.setEmail("test@example.com");
        user.setJob(jobs);

        when(principal.getName()).thenReturn("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        List<Job> allRetrievedJobs = jobService.getAllJobs(principal);

        assertEquals(2, allRetrievedJobs.size());
        assertSame(jobs, allRetrievedJobs);
    }

    @Test
    public void deleteJobTest_userExist() {
        Job job = new Job();
        job.setId(1L);
        User user = new User();

        user.setEmail("test@example.com");
        job.setUser(user);

        when(principal.getName()).thenReturn("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(jobRepository.findById(1L)).thenReturn(Optional.of(job));

        jobService.deleteJob(1L, principal);

    }
}