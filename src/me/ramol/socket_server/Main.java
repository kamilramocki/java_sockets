package me.ramol.socket_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<Socket> connectedUsers = new ArrayList<>();
    public static final int PORT_NUMBER = 3000;

    public static void main(String[] args) {

        try{

            ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);

            while (true) {

                Socket socket = serverSocket.accept();
                Thread clientThread = new Thread(new ClientRunnable(socket));
                clientThread.start();

                connectedUsers.add(socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}