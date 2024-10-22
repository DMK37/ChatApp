package client;

public class Main {
    public static void main(String[] args) {
        Client client = new Client("localhost", 5001);
        int port = client.run();
        new ChatClient("localhost", port);
    }
}