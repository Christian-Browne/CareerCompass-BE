package app.tracker.jobapplicatontracker.Security.Auth;

import Exceptions.JWTTokenExpiredException;
import app.tracker.jobapplicatontracker.Security.Auth.Entity.AuthRequest;
import app.tracker.jobapplicatontracker.Security.Auth.Entity.AuthResponse;
import app.tracker.jobapplicatontracker.Security.Auth.Entity.RegisterRequest;
import app.tracker.jobapplicatontracker.Security.Config.JwtService;
import app.tracker.jobapplicatontracker.Security.User.User;
import app.tracker.jobapplicatontracker.Security.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse resister(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        if (jwtService.isTokenExpired(jwtToken)) {
            throw new JWTTokenExpiredException();
        }

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
