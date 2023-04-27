package me.ramol.socket_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static final String HOST = "127.0.0.1";
    public static final int PORT_NUMBER = 3000;

    public static void main(String[] args) {

        try{
            Socket socket = new Socket(HOST, PORT_NUMBER);

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            Thread thread = new Thread(() -> {

                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    while (true) {

                        String line = reader.readLine();

                        if (line == null) {
                            break;
                        }

                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            thread.start();

            while (true) {
                String line = scanner.nextLine();
                writer.println(line);
            }

        } catch (IOException e){
            e.printStackTrace();
        }

    }
}