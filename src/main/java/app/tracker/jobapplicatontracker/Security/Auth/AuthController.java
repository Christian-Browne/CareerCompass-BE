package app.tracker.jobapplicatontracker.Security.Auth;

import app.tracker.jobapplicatontracker.Security.Auth.Entity.AuthRequest;
import app.tracker.jobapplicatontracker.Security.Auth.Entity.AuthResponse;
import app.tracker.jobapplicatontracker.Security.Auth.Entity.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.resister(request));
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(service.authenticate(request));

    }
}
