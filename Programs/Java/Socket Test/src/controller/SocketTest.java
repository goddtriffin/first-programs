package controller;

import model.Client;
import model.Server;
import view.GUI;

public class SocketTest{

    //socket
    private static Server server;
    private static Client client;

    //info
    private static String IP;
    private static int port;

    public SocketTest () {
        IP = "localhost";
        port = 30002;
    }

    public static void startServer () {
        if (client == null) {
            server = new Server(port);
            server.start();
        }
    }

    public static void startClient () {
        if (server ==  null) {
            client = new Client (IP, port);
            client.start();
        }
    }

    public static void close () {
        //server
        if (server != null) {
            server.close();
            server = null;
        }

        //client
        if (client != null) {
            client.close();
            client = null;
        }
    }

    public static String getIP () {
        return IP;
    }

    public static int getPort () {
        return port;
    }

    public static boolean isServerNull () {
        return (server == null);
    }

    public static boolean isClientNull () {
        return (client == null);
    }

    public static void main (String[] args) {
        new GUI();
    }
}