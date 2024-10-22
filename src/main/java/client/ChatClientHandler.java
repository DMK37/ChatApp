package client;

import java.io.DataInputStream;
import java.io.IOException;

public class ChatClientHandler implements Runnable {
    private final DataInputStream socketInput;

    public ChatClientHandler(DataInputStream socketInput) {
        this.socketInput = socketInput;
    }

    public void run() {
        String messageFromServer;
        try {
            // Listen for messages from the server
            while (true) {
                messageFromServer = socketInput.readUTF();
                System.out.println(messageFromServer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
