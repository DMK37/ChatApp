package server;

import java.io.*;
import java.net.Socket;
import java.util.Set;

public class MainServerHandler implements Runnable {
    private final Socket socket;
    private final Set<Integer> portsSet;
    private DataInputStream in;
    private DataOutputStream out;

    public MainServerHandler(Socket socket, Set<Integer> portsSet) {
        this.socket = socket;
        this.portsSet = portsSet;
    }

    @Override
    public void run() {
        try {
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());

            StringBuilder sb = new StringBuilder();
            for (Integer num : portsSet) {
                sb.append(num).append(" ");
            }

            out.writeUTF("Available chats: [ " + sb + "]");

            int chatPort = in.readInt();
            if (!portsSet.contains(chatPort)) {
                portsSet.add(chatPort);

                // start new thread with tcp chat server
                ChatServer chatServer = new ChatServer(chatPort);
                Thread thread = new Thread(chatServer);
                thread.setDaemon(true);
                thread.start();
            }
            out.writeUTF("Server is ready");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
