import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatForm extends JFrame {
    private final TextArea chatText;
    private final TextField chatField;
    private final JButton submitBtn;

    public ChatForm() {
        setTitle("Chat demo...");
        setBounds(400, 100, 800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        chatText = new TextArea();
        chatText.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        chatText.setEditable(false);

        contentPane.add(chatText, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        chatField = new TextField();
        bottomPanel.add(chatField, BorderLayout.CENTER);

        submitBtn = new JButton("Submit");
        bottomPanel.add(submitBtn, BorderLayout.EAST);

        ActionListener submitAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!chatField.getText().isBlank()) {
                    StringBuilder stringBuilder = new StringBuilder(chatText.getText());
                    stringBuilder.append(chatField.getText() + "\n");
                    chatText.setText(stringBuilder.toString());
                    chatField.setText("");
                    stringBuilder.setLength(0);
                }
            }
        };

        chatField.addActionListener(submitAction);
        submitBtn.addActionListener(submitAction);

        setVisible(true);
        chatField.requestFocusInWindow();
    }
}
