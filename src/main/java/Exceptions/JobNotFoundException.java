package Exceptions;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(Long id) {
        super("Could not find job with id of " + id);

    }
}
