package me.ramol.socket_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.function.Consumer;

public record ClientRunnable(Socket socket) implements Runnable {

    @Override
    public void run() {
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {

                String line = reader.readLine();

                if (line == null) {
                    break;
                }

                Main.connectedUsers.forEach(socket -> {
                    try {
                        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                        writer.println("Message: " + line);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });


                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}