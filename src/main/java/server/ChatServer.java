package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer implements Runnable {
    private Socket socket;
    private ServerSocket server;
    private final Set<ChatServerHandler> handlers = new HashSet<>();
    private final int port;

    public ChatServer(int port) {
        this.port = port;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                socket = server.accept();

                System.out.println("New connection to the chat: " + port);

                ChatServerHandler handler = new ChatServerHandler(socket, handlers);
                handlers.add(handler);
                Thread thread = new Thread(handler);
                thread.setDaemon(true);
                thread.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
