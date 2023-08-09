package app.tracker.jobapplicatontracker.Security.Config;

import app.tracker.jobapplicatontracker.Security.User.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((authorize) -> authorize
                    .requestMatchers(
                            HttpMethod.POST,
                            SecurityConstants.REGISTER_PATH,
                            SecurityConstants.AUTH_PATH
                    ).permitAll()
                    .requestMatchers(
                            HttpMethod.GET,
                            SecurityConstants.REGISTER_PATH,
                            SecurityConstants.AUTH_PATH,
                            SecurityConstants.DEMO_PATH,
                            SecurityConstants.DEMO_PATH_UPDATE_JOB_PATH,
                            SecurityConstants.OPEN_API_PATH,
                            SecurityConstants.SWAGGER_PATH,
                            SecurityConstants.SWAGGER2_PATH
                    ).permitAll()
                    .anyRequest().authenticated()
            )
            .sessionManagement((session) ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
