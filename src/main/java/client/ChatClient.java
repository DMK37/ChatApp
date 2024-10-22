package client;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    private Socket socket;
    private BufferedReader userInput;
    private DataOutputStream out;
    private DataInputStream socketInput;

    public ChatClient(String address, int port) {
        try {
            this.socket = new Socket(address, port);

            userInput = new BufferedReader(new InputStreamReader(System.in));
            socketInput = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(
                    socket.getOutputStream());

            Thread thread = new Thread(new ChatClientHandler(socketInput));
            thread.setDaemon(true);
            thread.start();

            String content;
            while (true) {
                content = userInput.readLine();
                out.writeUTF(new Message(content).toString());
                if (content.equals("$exit")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socketInput != null) socketInput.close();
                if (out != null) out.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
