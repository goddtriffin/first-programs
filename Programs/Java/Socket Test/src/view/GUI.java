package view;

import controller.SocketTest;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI extends JFrame {

    //main panel
    private JPanel mainPanel;


    public GUI() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(500,500);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Socket Test");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing (WindowEvent windowEvent) {
                SocketTest.close();
                System.exit(0);
            }
        });

        mainPanel = new MainGUI();
        add(mainPanel);

        setVisible(true);
    }
}
