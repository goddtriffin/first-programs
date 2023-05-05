package view;

import controller.SocketTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JPanel {

    //port
    private JLabel lPort;
    private JTextField tfPort;

    //action
    private JButton bAction;

    //console
    private JTextArea taConsole;

    public ServerGUI (int defaultPort) {
        //port
        lPort = new JLabel("Port: ");
        add(lPort);

        tfPort = new JTextField(defaultPort);
        add(tfPort);

        //action
        bAction = new JButton("Start");
        bAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent ae) {
                if (bAction.getText().equals("Start")) {
                    SocketTest.startServer();
                    bAction.setText("Stop");
                } else if (bAction.getText().equals("Stop")) {
                    SocketTest.close();
                    bAction.setText("Start");
                }
            }
        });
        add(bAction);

        //console
        taConsole = new JTextArea();
        taConsole.setPreferredSize(new Dimension(500,500));
        add(taConsole);
    }

    public static void appendConsole (String text) {

    }
}