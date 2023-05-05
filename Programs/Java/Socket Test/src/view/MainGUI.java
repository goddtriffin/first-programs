package view;

import controller.SocketTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JPanel {

    //content
    private JPanel contentPanel;
    private CardLayout layout;

    //server panel
    private JPanel serverPanel;

    //client panel
    private JPanel clientPanel;

    public MainGUI () {
        //server button
        JButton bServer = new JButton("Server");
        bServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent ae) {
                if (SocketTest.isClientNull()) {
                    layout.show(contentPanel, "server");
                }
            }
        });
        add(bServer);

        //client button
        JButton bClient = new JButton("Client");
        bClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent ae) {
                if (SocketTest.isServerNull()) {
                    layout.show(contentPanel, "client");
                }
            }
        });
        add(bClient);

        //content panel
        contentPanel = new JPanel();
        layout = new CardLayout();
        contentPanel.setLayout(layout);

        //server panel
        serverPanel = new ServerGUI(SocketTest.getPort());
        contentPanel.add(serverPanel, "server");

        //client panel
        clientPanel = new ClientGUI(SocketTest.getIP(), SocketTest.getPort());
        contentPanel.add(clientPanel, "client");

        add(contentPanel);
    }
}
