import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class Main {
    private JFrame frame;
    private JTextArea chatArea;
    private JTextField inputField;
    private boolean isLearning = false;
    private String lastUnknownInput = "";

    public Main() {
        
        frame = new JFrame("Gnirut Al");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout(10, 10));

        
        JLabel header = new JLabel("Gnirut Al", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 28));
        header.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        frame.add(header, BorderLayout.NORTH);

        
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chatArea.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(chatArea);
        frame.add(scrollPane, BorderLayout.CENTER);

       
        JPanel inputPanel = new JPanel(new BorderLayout(5, 0));
        inputField = new JTextField();
        JButton sendButton = new JButton("Send");
        
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(inputPanel, BorderLayout.SOUTH);

        
        ActionListener sendAction = e -> processInput();
        sendButton.addActionListener(sendAction);
        inputField.addActionListener(sendAction);

        frame.setVisible(true);
    }

    private void processInput() {
        String text = inputField.getText().trim();
        if (text.isEmpty()) return;

        appendChat("You", text);
        inputField.setText("");

        try {
            if (isLearning) {
                
                FileReader.appendToFile(text, "src/Response.txt");
                appendChat("Gnirut Al", "Thanks! I've learned that.");
                isLearning = false;
            } else {
                
                String response = Memory.getGuiResponse(text);
                
                if (response.equals("")) {
                    appendChat("Gnirut Al", "I don't know a valid answer. Please type what I should say:");
                    FileReader.appendToFile(text, "src/Memory.txt");
                    lastUnknownInput = text;
                    isLearning = true;
                } else {
                    appendChat("Gnirut Al", response);
                }
            }
        } catch (IOException ex) {
            appendChat("System", "Error accessing files.");
        }
    }

    private void appendChat(String user, String message) {
        chatArea.append(user + ": " + message + "\n\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}