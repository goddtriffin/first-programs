package net.magnusfrater;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ArpaSpeak.*;

public class Server {

    private Parley p;

    // a unique ID for each connection
    private static int uniqueId;
    // an ArrayList to keep the list of the Client
    private ArrayList<ClientThread> al;
    // to display time
    private SimpleDateFormat sdf;
    // the port number to listen for connection
    private int port;
    // the boolean that will be turned of to stop the server
    private boolean keepGoing;


    /*
     *  server constructor that receive the port to listen to for connection as parameter
     *  in console
     */
    public Server(Parley p, int port) {
        this.p = p;

        // the port
        this.port = port;
        // to display hh:mm:ss
        sdf = new SimpleDateFormat("HH:mm:ss");
        // ArrayList for the Client list
        al = new ArrayList<ClientThread>();
    }

    public void start() {
        p.appendParleyMessage("Starting server...");

        keepGoing = true;

        new ServerListener().start();
    }
    /*
     * For the GUI to stop the server
     */
    protected void stop() {
        keepGoing = false;
        // connect to myself as Client to exit statement
        // Socket socket = serverSocket.accept();
        try {
            new Socket("localhost", port);
        }
        catch(Exception e) {
            // nothing I can really do
        }
    }
    /*
     *  to broadcast a message to all Clients
     */
    private synchronized void broadcast(String message) {
        p.appendServerMessage(message);     // append in the room window

        if (p.jcbSpeak.isSelected()){
            new Thread(){
                public void run(){
                    String msg = message.substring(message.indexOf(":"));
                    new Speak(msg);
                }
            }.start();
        }

        // we loop in reverse order in case we would have to remove a Client
        // because it has disconnected
        for(int i = al.size(); --i >= 0;) {
            ClientThread ct = al.get(i);
            // try to write to the Client if it fails remove it from the list
            if(!ct.writeMsg(message)) {
                al.remove(i);
                p.appendParleyMessage("Disconnected Client " + ct.username + " removed from list.");
            }
        }
    }

    // for a client who logoff using the LOGOUT message
    synchronized void remove(int id) {
        // scan the array list until we found the Id
        for(int i = 0; i < al.size(); ++i) {
            ClientThread ct = al.get(i);
            // found it
            if(ct.id == id) {
                al.remove(i);
                return;
            }
        }
    }

    class ServerListener extends Thread {

        public void run() {
            /* create socket server and wait for connection requests */
            try
            {
                // the socket used by the server
                ServerSocket serverSocket = new ServerSocket(port);

                // infinite loop to wait for connections
                while(keepGoing)
                {
                    // format message saying we are waiting
                    p.appendParleyMessage("Server waiting for Clients on port " + port + ".");

                    Socket socket = serverSocket.accept();  	// accept connection
                    // if I was asked to stop
                    if(!keepGoing)
                        break;
                    ClientThread t = new ClientThread(socket);  // make a thread of it
                    al.add(t);									// save it in the ArrayList
                    t.start();
                }
                // I was asked to stop
                try {
                    serverSocket.close();
                    for(int i = 0; i < al.size(); ++i) {
                        ClientThread tc = al.get(i);
                        try {
                            tc.sInput.close();
                            tc.sOutput.close();
                            tc.socket.close();
                        }
                        catch(IOException ioE) {
                            // not much I can do
                        }
                    }
                }
                catch(Exception e) {
                    p.appendParleyMessage("Exception closing the server and clients: " + e);
                }
            }
            // something went bad
            catch (IOException e) {
                String msg = sdf.format(new Date()) + " Exception on new ServerSocket: " + e + "\n";
                p.appendParleyMessage(msg);
            }

            p.appendParleyMessage("Server stopped.");
        }
    }

    /** One instance of this thread will run for each client */
    class ClientThread extends Thread {
        // the socket where to listen/talk
        Socket socket;
        ObjectInputStream sInput;
        ObjectOutputStream sOutput;
        // my unique id (easier for disconnection)
        int id;
        // the Username of the Client
        String username;
        // the only type of message a will receive
        ChatMessage cm;
        // the date I connect
        String date;

        // Constructore
        ClientThread(Socket socket) {
            // a unique id
            id = ++uniqueId;
            this.socket = socket;
			/* Creating both Data Stream */
            p.appendParleyMessage("Thread trying to create Object Input/Output Streams");
            try
            {
                // create output first
                sOutput = new ObjectOutputStream(socket.getOutputStream());
                sInput  = new ObjectInputStream(socket.getInputStream());
                // read the username
                username = (String) sInput.readObject();
                p.appendParleyMessage(username + " just connected.");
            }
            catch (IOException e) {
                p.appendParleyMessage("Exception creating new Input/output Streams: " + e);
                return;
            }
            // have to catch ClassNotFoundException
            // but I read a String, I am sure it will work
            catch (ClassNotFoundException e) {
            }
            date = new Date().toString() + "\n";
        }

        // what will run forever
        public void run() {
            // to loop until LOGOUT
            boolean keepGoing = true;
            while(keepGoing) {
                // read a String (which is an object)
                try {
                    cm = (ChatMessage) sInput.readObject();
                }
                catch (IOException e) {
                    p.appendParleyMessage(username + " Exception reading Streams: " + e);
                    break;
                }
                catch(ClassNotFoundException e2) {
                    break;
                }
                // the message part of the ChatMessage
                String message = cm.getMessage();

                // Switch on the type of message receive
                switch(cm.getType()) {

                    case ChatMessage.MESSAGE:
                        broadcast("<"+ username + ">: " + message);
                        break;
                    case ChatMessage.LOGOUT:
                        p.appendParleyMessage(username + " disconnected with a LOGOUT message.");
                        keepGoing = false;
                        break;
                    case ChatMessage.WHOISIN:
                        writeMsg("List of the users connected at " + sdf.format(new Date()) + "\n");
                        // scan al the users connected
                        for(int i = 0; i < al.size(); ++i) {
                            ClientThread ct = al.get(i);
                            writeMsg((i+1) + ") " + ct.username + " since " + ct.date);
                        }
                        break;
                }
            }
            // remove myself from the arrayList containing the list of the
            // connected Clients
            remove(id);
            close();
        }

        // try to close everything
        private void close() {
            // try to close the connection
            try {
                if(sOutput != null) sOutput.close();
            }
            catch(Exception e) {}
            try {
                if(sInput != null) sInput.close();
            }
            catch(Exception e) {};
            try {
                if(socket != null) socket.close();
            }
            catch (Exception e) {}
        }

        /*
         * Write a String to the Client output stream
         */
        private boolean writeMsg(String msg) {
            // if Client is still connected send the message to it
            if(!socket.isConnected()) {
                close();
                return false;
            }
            // write the message to the stream
            try {
                sOutput.writeObject(msg);
            }
            // if an error occurs, do not abort just inform the user
            catch(IOException e) {
                p.appendParleyMessage("Error sending message to " + username);
                p.appendParleyMessage(e.toString());
            }
            return true;
        }
    }
}