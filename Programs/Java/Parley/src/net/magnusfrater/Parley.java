package net.magnusfrater;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parley extends JFrame implements FocusListener{

    //FORM COMPONENTS
    private final Dimension minSize = new Dimension(700,400);
    private final Insets jtfMargin = new Insets(0,3,1,5);
    //top
    private ButtonGroup bg;
    private JRadioButton jrbServer;
    private JRadioButton jrbClient;

    private JLabel jlHostIP;
    private JTextField jtfHostIP;

    private JLabel jlHostPort;
    private JTextField jtfHostPort;

    private JLabel jlName;
    private JTextField jtfUsername;

    protected JCheckBox jcbSpeak;
    private JButton jbInfo;

    private JButton jbClientServerAction;
    //mid
    private JTextArea jtaDisplay;
    private JScrollPane jspDisplay;
    //bot
    private JTextField jtfInput;

    //NETWORKING
    private String hostIP;
    private int hostPort;
    private String username;

    private Server server;
    private Client client;

    //info
    String info =
            " CMUdict  --  Major Version: 0.07\n" +
                    " ========================================================================\n" +
                    " Copyright (C) 1993-2015 Carnegie Mellon University. All rights reserved.\n" +
                    " \n" +
                    " Redistribution and use in source and binary forms, with or without\n" +
                    " modification, are permitted provided that the following conditions\n" +
                    " are met:\n" +
                    " \n" +
                    "  1. Redistributions of source code must retain the above copyright\n" +
                    "     notice, this list of conditions and the following disclaimer.\n" +
                    "     The contents of this file are deemed to be source code.\n" +
                    " \n" +
                    "  2. Redistributions in binary form must reproduce the above copyright\n" +
                    "     notice, this list of conditions and the following disclaimer in\n" +
                    "     the documentation and/or other materials provided with the\n" +
                    "     distribution.\n" +
                    " \n" +
                    " This work was supported in part by funding from the Defense Advanced\n" +
                    " Research Projects Agency, the Office of Naval Research and the National\n" +
                    " Science Foundation of the United States of America, and by member\n" +
                    " companies of the Carnegie Mellon Sphinx Speech Consortium. We acknowledge\n" +
                    " the contributions of many volunteers to the expansion and improvement of\n" +
                    " this dictionary.\n" +
                    " \n" +
                    " THIS SOFTWARE IS PROVIDED BY CARNEGIE MELLON UNIVERSITY ``AS IS'' AND\n" +
                    " ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,\n" +
                    " THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR\n" +
                    " PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL CARNEGIE MELLON UNIVERSITY\n" +
                    " NOR ITS EMPLOYEES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,\n" +
                    " SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT\n" +
                    " LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,\n" +
                    " DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY\n" +
                    " THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT\n" +
                    " (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE\n" +
                    " OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.\n" +
                    " \n" +
                    " ========================================================================\n" +
                    "\n" +
                    "  NOTES\n" +
                    "\n" +
                    "- [20080401] (air)  New dict file format introduced\n" +
                    "- comments (like this section) are allowed\n" +
                    "- file name is major version; vers/rev information is now in the header\n";

    /*
     * Constructor to create JFrame, call GUI setup, and initialize IP/port/name
     */
    public Parley(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(minSize);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Parley: By Todd Griffin");
        setBackground(Color.WHITE);

        //init chat variables
        hostIP = "127.0.0.1";
        hostPort = 43336;
        username = "Anonymous";

        server = null;
        client = null;

        initComponents(); //initializes all components for use
        initClientComponents(); //enables only client-needed components only

        setVisible(true); //everything is initialized, ready to be seen

        appendParleyMessage("Welcome to Parley, the simple instant messenger!"); //welcome message
    }

    /*
         * Initializes entire GUI
         */
    private void initComponents(){
        //initialize panels
        JPanel top = new JPanel(new GridBagLayout());
        JPanel mid = new JPanel(new BorderLayout());
        JPanel bot = new JPanel(new BorderLayout());

        //TOP
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,0,0);

        bg = new ButtonGroup();

        gbc.gridx = 0; //server radio button
        gbc.gridy = 0;
        jrbServer = new JRadioButton("Server");
        jrbServer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                initServerComponents();
            }
        });
        bg.add(jrbServer);
        top.add(jrbServer,gbc);

        gbc.gridx = 0;  //client radio button
        gbc.gridy = 1;
        jrbClient = new JRadioButton("Client");
        jrbClient.setSelected(true);
        jrbClient.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                initClientComponents();
            }
        });
        bg.add(jrbClient);
        top.add(jrbClient,gbc);

        gbc.gridx = 1; //host IP label
        gbc.gridy = 0;
        jlHostIP = new JLabel("Host IP: ");
        top.add(jlHostIP,gbc);

        gbc.gridx = 1; //host IP text field
        gbc.gridy = 1;
        jtfHostIP = new JTextField(hostIP);
        jtfHostIP.setMargin(jtfMargin);
        jtfHostIP.addFocusListener(this);
        jtfHostIP.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (jtfHostIP.getText().equals("localhost")){
                    hostIP = "127.0.0.1";
                }else{
                    hostIP = jtfHostIP.getText();
                }

                jtfHostIP.setText(hostIP);
            }
        });
        top.add(jtfHostIP,gbc);

        gbc.gridx = 2; //host port label
        gbc.gridy = 0;
        jlHostPort = new JLabel("Host Port: ");
        top.add(jlHostPort,gbc);

        gbc.gridx  = 2; //host port text field
        gbc.gridy = 1;
        jtfHostPort = new JTextField(String.valueOf(hostPort));
        jtfHostPort.setMargin(jtfMargin);
        jtfHostPort.addFocusListener(this);
        jtfHostPort.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                hostPort = Integer.valueOf(jtfHostPort.getText());

                jtfHostPort.setText(String.valueOf(hostPort));
            }
        });
        top.add(jtfHostPort,gbc);

        gbc.gridx = 3; //username label
        gbc.gridy = 0;
        jlName = new JLabel("Username: ");
        top.add(jlName,gbc);

        gbc.gridx = 3; //username text field
        gbc.gridy = 1;
        jtfUsername = new JTextField(username);
        jtfUsername.setMargin(jtfMargin);
        jtfUsername.addFocusListener(this);
        jtfUsername.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (jtfUsername.getText().indexOf(":")==-1 && jtfUsername.getText().indexOf("<")==-1 && jtfUsername.getText().indexOf(">")==-1)
                    username = jtfUsername.getText();

                jtfUsername.setText(username);
            }
        });
        top.add(jtfUsername,gbc);

        gbc.gridx = 4; //speak check box
        gbc.gridy = 0;
        jcbSpeak = new JCheckBox("Speak");
        top.add(jcbSpeak,gbc);

        gbc.gridx = 4; //database info
        gbc.gridy = 1;
        jbInfo = new JButton("Info");
        jbInfo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                JOptionPane.showMessageDialog(new JFrame(), info);
            }
        });
        top.add(jbInfo,gbc);

        gbc.gridx = 5; //client/server action button
        gbc.gridy = 1;
        jbClientServerAction = new JButton("JButton");
        jbClientServerAction.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (jbClientServerAction.getText().equals("Start")){ //if server, primed for client connection=
                    startServer();
                }else if (jbClientServerAction.getText().equals("Stop")){ //if server, primed for client disconnect
                    stopServer();
                }else if (jbClientServerAction.getText().equals("Join")){ //if client, primed to attempt server connection
                    startClient();
                }else if (jbClientServerAction.getText().equals("Leave")){ //if client, primed to attempt server disconnection
                    stopClient();
                }
            }
        });
        top.add(jbClientServerAction,gbc);

        //MID
        jtaDisplay = new JTextArea();
        jspDisplay = new JScrollPane(jtaDisplay);
        jtaDisplay.setEditable(false);
        jtaDisplay.setLineWrap(true);
        jtaDisplay.setMargin(new Insets(0,5,5,0));
        mid.add(jspDisplay);

        DefaultCaret caret = (DefaultCaret)jtaDisplay.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        mid.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        //BOT
        jtfInput = new JTextField(); //user input text field
        jtfInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                client.sendMessage(new ChatMessage(ChatMessage.MESSAGE, jtfInput.getText()));

                jtfInput.setText("");
            }
        });
        bot.add(jtfInput);

        bot.setBorder(BorderFactory.createEmptyBorder(0,5,5,5));

        //add to frame
        add(top, BorderLayout.NORTH);
        add(mid, BorderLayout.CENTER);
        add(bot, BorderLayout.SOUTH);

        pack();
    }

    /*
     * Updates chat display when message comes from the server
     */
    protected void appendServerMessage(String message){
        String output = "";

        DateFormat df = new SimpleDateFormat("h:mm a"); //current time in hours:minutes AM/PM
        output += ("["+ df.format(new Date()) +"] "); //adds time message was received

        output += message; //adds message

        jtaDisplay.append(output +"\n");
    }

    /*
     * Updates chat display when message comes from your client
     */
    protected void appendParleyMessage(String message){
        String output = "";

        DateFormat df = new SimpleDateFormat("h:mm a"); //current time in hours:minutes AM/PM
        output += ("["+ df.format(new Date()) +"] "); //adds time message was sent

        output += "<PARLEY>: "; //labels this as a PARLEY message

        output += message; //adds message

        jtaDisplay.append(output +"\n");
    }

    @Override
    public void focusGained(FocusEvent e) {
        //not used
    }
    @Override
    public void focusLost(FocusEvent e) {
        Component c = e.getComponent();

        if (c == jtfHostIP) //host IP text field
            jtfHostIP.setText(hostIP);

        if (c == jtfHostPort) //host port text field
            jtfHostPort.setText(String.valueOf(hostPort));

        if (c == jtfUsername) //username text field
            jtfUsername.setText(username);
    }

    private void initServerComponents(){
        jrbServer.setEnabled(true); //allowed to specify if server
        jrbClient.setEnabled(true); //allowed to specify if client

        jtfHostIP.setEnabled(false); //server uses its own address
        jtfHostPort.setEnabled(true); //server can specify which port to listen
        jtfUsername.setEnabled(false); //servers don't need usernames

        jbClientServerAction.setEnabled(true); //allowed to attempt server startup
        jbClientServerAction.setText("Start"); //set text relevant to expected action

        jtfInput.setEnabled(false); //server cannot send own messages
    }
    private void initClientComponents(){
        jrbServer.setEnabled(true); //allowed to specify if server
        jrbClient.setEnabled(true); //allowed to specify if client

        jtfHostIP.setEnabled(true); //need to specify server's address
        jtfHostPort.setEnabled(true); //need to specify server's port
        jtfUsername.setEnabled(true); //every user needs a username

        jbClientServerAction.setEnabled(true); //allowed to attempt server connection
        jbClientServerAction.setText("Join"); //set text relevant to expected action

        jtfInput.setEnabled(false); //client can't send data until connected to server
    }
    private void initListeningComponents(){
        jrbServer.setEnabled(false); //cannot change to server while listening
        jrbClient.setEnabled(false); //cannot change to client while listening

        jtfHostIP.setEnabled(false); //cannot change host IP while listening
        jtfHostPort.setEnabled(false); //cannot change host port while listening
        jtfUsername.setEnabled(false); //cannot change username while listening

        jbClientServerAction.setEnabled(true); //allowed to attempt (leaving server) or (stopping server)
        if (jrbServer.isSelected())
            jbClientServerAction.setText("Stop"); //if server, allowed to stop server
        if (jrbClient.isSelected()){
            jbClientServerAction.setText("Leave"); //if client, allowed to leave server
            jtfInput.setEnabled(true); //if client, allowed to send data to server
        }
    }

    private void startClient(){
        initListeningComponents();
        appendParleyMessage("Connecting to server IP \'"+ hostIP +"\' on port "+ hostPort +"...");

        client = new Client(this, hostIP, hostPort, username);
        if (!client.start()){
            client = null;
            initClientComponents();
        }
    }
    private void stopClient(){
        client.sendMessage(new ChatMessage(ChatMessage.LOGOUT, ""));
        client.disconnect();
        client = null;

        appendParleyMessage("Left server.");
        initClientComponents();
    }

    private void startServer(){
        initListeningComponents();

        server = new Server(this, hostPort);
        server.start();
    }
    private void stopServer(){
        server.stop();
        server = null;

        initServerComponents();
    }

    public static void main(String[] args){
        Parley p = new Parley();
    }
}

//research runnable, threads, synchronized, synchronized variables

//http://www.dreamincode.net/forums/topic/259777-a-simple-chat-program-with-clientserver-gui-optional/