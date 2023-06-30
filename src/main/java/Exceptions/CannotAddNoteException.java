package Exceptions;

public class CannotAddNoteException extends RuntimeException{
    public CannotAddNoteException(Long jobId) {
        super("Cannot add note because job with the id of `" + jobId + "` doesn't exist");
    }
}
