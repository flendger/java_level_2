import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 8765;
    private final static String EXIT_CHAT = "/exit";
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner scanner;

    public ChatClient() {
        openConnection();

        scanner = new Scanner(System.in);
        sendMsg();
        scanner.close();
    }

    private void sendMsg(){
        while (!socket.isClosed()){
            try {
                System.out.println("Enter msg: ");
                String msg = scanner.nextLine();
                if (!socket.isClosed()){
                    out.writeUTF(msg);
                }
                if (msg.equals(EXIT_CHAT)) {
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void listenServer(){
        while (true) {
            try {
                String msg = in.readUTF();
                if (msg.equals(EXIT_CHAT)) {
                    out.writeUTF(EXIT_CHAT);
                    break;
                }
                System.out.println("Server: " + msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        closeConnection();
    }

    private void openConnection(){
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    listenServer();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection(){
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Connection closed...");
        try {
            System.in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
