package net.magnusfrater;

import java.io.IOException;
import java.net.ServerSocket;

public class PortFinder {

    private int findPort(){
        ServerSocket ss = null;

        try {
            ss = new ServerSocket(0);
            ss.setReuseAddress(true);

            int port = ss.getLocalPort();

            try { //always close socket
                ss.close();
            } catch (IOException e) { //shouldn't need to catch
                System.out.println(e);
            }

            return port;
        } catch (IOException e){
            System.out.println(e);
        } finally {
            if (ss != null){ //in case it wasn't closed before
                try {
                    ss.close();
                } catch (IOException e) { //shouldn't need to catch
                    System.out.println(e);
                }
            }
        }
        throw new IllegalStateException("Could not find a free TCP/IP port to start embedded Jetty HTTP Server on.");
    }

    public static void main(String[] args){
        PortFinder pf = new PortFinder();

        System.out.println(pf.findPort());
    }
}