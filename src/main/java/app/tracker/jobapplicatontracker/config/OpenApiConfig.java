package app.tracker.jobapplicatontracker.config;

import app.tracker.jobapplicatontracker.JobApplicatonTrackerApplication;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                .title("Job Application Tracker API")
                .description("An api that can track job applications")
                .version("v1.0"));
    }
}
