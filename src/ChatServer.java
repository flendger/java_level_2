import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ChatServer {
    private final static int SERVER_PORT = 8765;
    private final static String EXIT_CHAT = "/exit";
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner scanner;
    private AuthService authService;
    private Set<ClientHandler> clientHandlers;

    public ChatServer() {
        startServer();
    }

    private void sendMsg() {
        while (true) {
                System.out.println("Enter msg:");
                String msg = scanner.nextLine();
                broadcastMessage("Server: " + msg);
                if (msg.equals(EXIT_CHAT)) {
                    scanner.close();
                    break;
                }
        }
    }

    private void startServer(){
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)){
            System.out.println(String.format("Server is running on port: %d", serverSocket.getLocalPort()));

            authService = new StaticAuthService();
            clientHandlers = new HashSet<>();

            scanner = new Scanner(System.in);
            Thread inputThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    sendMsg();
                }
            });
            inputThread.setDaemon(true);
            inputThread.start();

            while (true){
                System.out.println("Waiting for connections...");
                Socket socket = serverSocket.accept();
                System.out.println("Connection established with " + socket);
                new ClientHandler(this, socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server stopped...");
    }
    public AuthService getAuthService() {
        return authService;
    }

    public synchronized boolean isOccupied(AuthService.Record record) {
        for (ClientHandler ch : clientHandlers) {
            if (ch.getRecord().equals(record)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void subscribe(ClientHandler ch) {
        clientHandlers.add(ch);
    }

    public synchronized void unsubscribe(ClientHandler ch) {
        clientHandlers.remove(ch);
    }

    public synchronized void broadcastMessage(String message) {
        for (ClientHandler ch : clientHandlers) {
            ch.sendMessage(message);
        }
    }

}
