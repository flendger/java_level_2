import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClientGUI extends JFrame {
    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 8765;
    private final static String EXIT_CHAT = "/exit";
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private final JTextArea chatText = new JTextArea();
    private final JTextField chatField = new JTextField();
    private final JButton submitBtn = new JButton("Submit");

    public ChatClientGUI() {
        prepareGUI();
        openConnection();
    }

    private void prepareGUI() {
        setTitle("Chat client...");
        setBounds(400, 100, 800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        chatText.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        chatText.setEditable(false);

        contentPane.add(chatText, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        bottomPanel.add(chatField, BorderLayout.CENTER);

        bottomPanel.add(submitBtn, BorderLayout.EAST);

        ActionListener submitAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!chatField.getText().isBlank()) {
                    if (!socket.isClosed()){
                        try {
                            String msg = chatField.getText();
                            out.writeUTF(msg);
                            chatField.setText("");
                            chatField.grabFocus();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }

                }
            }
        };

        chatField.addActionListener(submitAction);
        submitBtn.addActionListener(submitAction);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                try {
                    if (!socket.isClosed()){
                        out.writeUTF(EXIT_CHAT);
                    }
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
            }
        });

        setVisible(true);
        chatField.setFocusable(true);
        chatField.grabFocus();
    }

    private void listenServer(){
        while (true) {
            try {
                String msg = in.readUTF();
                if (msg.equals(EXIT_CHAT)) {
                    out.writeUTF(EXIT_CHAT);
                    break;
                }
                addMessageToChatField(msg);
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

    private void addMessageToChatField(String msg){
        StringBuilder stringBuilder = new StringBuilder(chatText.getText());
        stringBuilder.append(msg + "\n");
        chatText.setText(stringBuilder.toString());
        stringBuilder.setLength(0);
    }
}
