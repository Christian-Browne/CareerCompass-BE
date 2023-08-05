package app.tracker.jobapplicatontracker.Repository;

import app.tracker.jobapplicatontracker.Entity.Job;
import app.tracker.jobapplicatontracker.Entity.JobIntroInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobRepository extends CrudRepository<Job, Long> {
//    List<JobIntroInfo> findAllProjectedByOrderById();
    List<Job> findAllByUserIdOrderById(Integer userId);
}
