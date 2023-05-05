package view;

import controller.SocketTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JPanel {

    //IP
    private JLabel lIP;
    private JTextField tfIP;

    //port
    private JLabel lPort;
    private JTextField tfPort;

    //action
    private JButton bAction;

    //console
    private JTextArea taConsole;

    public ClientGUI (String defaultIP, int defaultPort) {
        //IP
        lIP = new JLabel("IP: ");
        add(lIP);

        tfIP = new JTextField(defaultIP);
        add(tfIP);

        //port
        lPort = new JLabel("Port: ");
        add(lPort);

        tfPort = new JTextField(String.valueOf(defaultPort));
        add(tfPort);

        //action
        bAction = new JButton("Connect");
        bAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent ae) {
                if (bAction.getText().equals("Connect")) {
                    SocketTest.startClient();
                    bAction.setText("Disconnect");
                } else if (bAction.getText().equals("Disconnect")) {
                    SocketTest.close();
                    bAction.setText("Connect");
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
