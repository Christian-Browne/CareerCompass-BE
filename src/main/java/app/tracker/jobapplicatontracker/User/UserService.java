package app.tracker.jobapplicatontracker.User;

public interface UserService {
    User getUser(Long id);
    User saveUser(User user);
}
