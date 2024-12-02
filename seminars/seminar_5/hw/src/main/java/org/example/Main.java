package org.example;

import org.example.client.ClientGUI;
import org.example.server.ServerWindow;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ServerWindow serverWindow = new ServerWindow();
        ClientGUI clientGUI1 = new ClientGUI(serverWindow);
        ClientGUI clientGUI2 = new ClientGUI(serverWindow);

        // Добавляем клиента в список подключенных клиентов
        serverWindow.addUser(clientGUI1);
        serverWindow.addUser(clientGUI2);
        // Запуск окна-сервера
        serverWindow.btnStart.doClick();

        // Подключение клиента к серверу
        clientGUI1.connectToServer("localhost", 1400);
        clientGUI2.connectToServer("localhost", 1400);

        try {
            ServerSocket serverSocket = new ServerSocket(1400);
            Server server = new Server(serverSocket);
            server.runServer();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите свое имя: ");
            String name = scanner.nextLine();
            Socket socket = new Socket("localhost", 1400);
            Client client = new Client(socket, name);
            InetAddress inetAddress = socket.getInetAddress();
            System.out.println("InetAddress: " + inetAddress);
            String remoteIp = inetAddress.getHostAddress();
            System.out.println("Remote IP: " + remoteIp);
            System.out.println("LocalPort: " + socket.getLocalPort());

            client.listenForMessage();
            client.sendMessage();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}