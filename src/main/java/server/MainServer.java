package server;

// A Java program for a Server
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainServer {
    private static final Set<Integer> portsSet = new HashSet<>();
    private Socket socket;
    private ServerSocket server;

    public MainServer(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            while (true) {
                socket = server.accept();
                System.out.println("Client accepted");

                MainServerHandler handler = new MainServerHandler(socket, portsSet);
                Thread thread = new Thread(handler);
                thread.setDaemon(true);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

