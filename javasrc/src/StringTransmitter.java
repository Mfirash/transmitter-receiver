import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class StringTransmitter {

    public static void main(String[] args) {
        String serverAddress = "localhost"; //replace this
        int serverPort = 12345; //replace this 
        JFrame frame = new JFrame("Transmitter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        JPanel panel = new JPanel();
        JTextField textField = new JTextField(20);
        JButton button = new JButton("Send");
        panel.setLayout(new GridLayout(1, 2));
        panel.add(textField);
        panel.add(button); 
        frame.add(panel);     

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = textField.getText();
                try (Socket socket = new Socket(serverAddress, serverPort);
                     PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {
                    out.println(message); 
                    System.out.println("sent: " + message);
                } catch (IOException ex) {
                    System.err.println("error sending message: " + ex.getMessage());
                }
                textField.setText(""); 
            }
        }); 
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.doClick();
            }
        });
        frame.setVisible(true); 
       
    }    
    
}