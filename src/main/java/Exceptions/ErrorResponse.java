package Exceptions;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private ArrayList<String> messages = new ArrayList<>();
    private long status;

    public ErrorResponse(String message, long status) {
        this.messages.add(message);
        this.status = status;
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
