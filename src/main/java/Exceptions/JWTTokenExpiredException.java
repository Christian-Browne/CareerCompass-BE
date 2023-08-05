package Exceptions;

public class JWTTokenExpiredException extends RuntimeException{
    public JWTTokenExpiredException() {
        super("Could not validate user");
    }
}
