package org.example;

import org.example.client.ClientGUI;
import org.example.server.ServerWindow;

public class Main {
    public static void main(String[] args) {

        ServerWindow serverWindow = new ServerWindow();
        serverWindow.connectToServer("localhost", 1400);
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


    }
}