import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
    private final static int SERVER_PORT = 8765;
    private final static String EXIT_CHAT = "/exit";
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner scanner;

    public ChatServer() {
        startServer();
    }

    private void listenClient() {
        while (true) {
            try {
                String msg = in.readUTF();

                if (msg.equals(EXIT_CHAT)) {
                    out.writeUTF(EXIT_CHAT);
                    break;
                }
                System.out.println(String.format("Client: %s", msg));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMsg() {
        while (true) {
            try {
                System.out.println("Enter msg:");
                String msg = scanner.nextLine();
                out.writeUTF(msg);
                if (msg.equals(EXIT_CHAT)) {
                    scanner.close();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void startServer(){
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println(String.format("Server is running on port: %d", serverSocket.getLocalPort()));
            socket = serverSocket.accept();

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            scanner = new Scanner(System.in);
            Thread inputThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    sendMsg();
                }
            });
            inputThread.setDaemon(true);
            inputThread.start();

            listenClient();

            stopServer();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopServer(){
        try {
            serverSocket.close();
            System.out.println("Server stopped...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
