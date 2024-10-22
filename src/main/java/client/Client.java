package client;

import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;
    private BufferedReader userInput;
    private DataOutputStream out;
    private DataInputStream socketInput;

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);

            userInput = new BufferedReader(new InputStreamReader(System.in));
            socketInput = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(
                    socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int run() {
        String input;
        String socketMessage;
        int chatPort = -1;

        try {
            socketMessage = socketInput.readUTF();
            System.out.println(socketMessage);
            System.out.println("Provide port number of the Chat:");

            input = userInput.readLine();
            chatPort = Integer.parseInt(input);
            out.writeInt(chatPort);

            socketMessage = socketInput.readUTF();
            System.out.println(socketMessage);

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        } finally {
            try {
                socketInput.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return chatPort;
    }
}
