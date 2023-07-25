package app.tracker.jobapplicatontracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class JobApplicatonTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobApplicatonTrackerApplication.class, args);
    }

}
