package org.example.chat.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final Socket socket;
    private final String name;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    
    public Client(Socket socket, String userName) {
        this.socket = socket;
        name = userName;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }

    }

    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter !=null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String messageFromGroup;
                while (socket.isConnected()) {
                    try {
                        messageFromGroup = bufferedReader.readLine();
                        System.out.println(messageFromGroup);
                    } catch (IOException e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public void sendMessage() {
        try {
            bufferedWriter.write(name);
            bufferedWriter.newLine();
            bufferedWriter.flush(); // метод .flush() отправляет сообщение/данные на сервер

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String message = scanner.nextLine();
                bufferedWriter.write(name + ": " + message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
}