package Exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    LocalDateTime timestamp;
    private ArrayList<String> messages = new ArrayList<>();
    private long status;

    public ErrorResponse(String message, long status) {
        this.timestamp = LocalDateTime.now();
        this.messages.add(message);
        this.status = status;
    }
    public ErrorResponse(ArrayList<String> errors, long status) {
        this.timestamp = LocalDateTime.now();
        this.messages = errors;
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime time) {
        this.timestamp = time;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }
}
