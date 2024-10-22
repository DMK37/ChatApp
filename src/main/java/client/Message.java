package client;

import java.time.LocalDateTime;

public class Message {

    private final String content;
    private final String timestamp;

    public Message(String content) {
        this.content = content;
        this.timestamp = LocalDateTime.now().toString();
    }

    public String getContent() {
        return content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return timestamp + " : " + content;
    }
}
