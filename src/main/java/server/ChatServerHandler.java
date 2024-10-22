package server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Set;

public class ChatServerHandler implements Runnable {
    private final Socket socket;
    private final Set<ChatServerHandler> handlers;
    private DataInputStream in;
    private DataOutputStream out;

    public ChatServerHandler(Socket socket, Set<ChatServerHandler> handlers) {
        this.socket = socket;
        this.handlers = handlers;
    }

    @Override
    public void run() {
        try {
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String message = in.readUTF();
                if(message.contains("$exit")) {
                    handlers.remove(this);
                    break;
                }
                for (ChatServerHandler handler : handlers) {
                    if (handler != this) {
                        handler.out.writeUTF(message);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                socket.close();
                handlers.remove(ChatServerHandler.this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
