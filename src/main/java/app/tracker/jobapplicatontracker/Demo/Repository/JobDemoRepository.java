package app.tracker.jobapplicatontracker.Demo.Repository;

import app.tracker.jobapplicatontracker.Demo.Entity.JobDemo;
import app.tracker.jobapplicatontracker.Demo.Entity.JobDemoIntroInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobDemoRepository extends CrudRepository<JobDemo, Long> {
    List<JobDemo> findAllProjectedByOrderById();
}
