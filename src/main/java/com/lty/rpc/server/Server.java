package com.lty.rpc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by LTY on 6/14/16.
 */
public class Server {

    private static final int PORT = 8080;

    public static void main(String[] args) {
        exportRpc();
    }

    private static void exportRpc() {
        try {
            ServerSocket server = new ServerSocket(PORT);
            while(true){
                Socket s = server.accept();
                new RpcThread(s).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
