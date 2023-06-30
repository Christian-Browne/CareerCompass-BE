package Exceptions;

public class NoteNotFoundException extends RuntimeException{
    public NoteNotFoundException(Long noteId) {
        super("Cannot find note with the id of `" + noteId + "`");
    }
}
