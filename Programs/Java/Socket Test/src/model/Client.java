package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    //server info
    String serverIP;
    int serverPort;

    //socket
    private Socket socket;

    //out
    private PrintWriter out;

    //in
    private BufferedReader in;
    private Thread threadIn;

    public Client (String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public void start () {
        //open in descending order
        try {
            //socket
            socket = new Socket(serverIP, serverPort);

            //out
            out = new PrintWriter(socket.getOutputStream(), true);

            //in
            in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()
                    )
            );

        } catch (IOException ioe) {
            System.out.println("IOE: "+ ioe);
        }

        threadIn = new Thread()
    }

    public void close () {
        //close in ascending order
        try {
            //in
            if (in != null) in.close();

            //out
            if (out != null) out.close();

            //socket
            if (socket != null) socket.close();

        } catch (IOException ioe) {
            System.out.println("IOE: "+ ioe);
        }
    }
}
