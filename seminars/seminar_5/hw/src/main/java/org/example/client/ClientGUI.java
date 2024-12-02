package org.example.client;

import org.example.server.ServerWindow ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientGUI extends JFrame {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    private final JTextArea msgLog = new JTextArea();
    private final ServerWindow serverWindow;
    private boolean loginConnection;

    JPanel topFields = new JPanel(new GridLayout(3, 2));
    JTextField ip = new JTextField("000.0.0.0");
    JTextField port = new JTextField("8080");
    JPasswordField password = new JPasswordField("54321");
    JButton btnLogin = new JButton("Login");

    private static final String[] login ={"V_M", "D_S", "S_D"};

    private final JComboBox login_list = new JComboBox<>(login); // JComboBox - для выпадающего списка

    JPanel bottomFields = new JPanel(new BorderLayout());
    JPanel msgField = new JPanel(new BorderLayout());
    JTextField msg = new JTextField(/*"some message"*/);
    JButton btnSend = new JButton("Send");

    public void sendMessage() {
        if(loginConnection == false) {
            msgLog.append("User is not connected.\n");
            return;
        }
        if (msg.getText().isEmpty()) {
            msgLog.append("Message cannot be empty.\n");
            return;
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        String selectedUser = (String) login_list.getSelectedItem();
        String message = "Date: " + localDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss")) + " user: " + selectedUser + ": " + msg.getText();
        if (serverWindow.isServerWorking()) {
            msg.setText("");
            serverWindow.broadcastMessage(message);
        } else {
            msgLog.append("Server is not running\n");
        }
    }

    public ClientGUI(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat group");

        topFields.add(ip);
        topFields.add(port);
        topFields.add(login_list);
        topFields.add(password);
        topFields.add(btnLogin);
        add(topFields, BorderLayout.NORTH);

        msgField.add(msg, BorderLayout.CENTER);
        msgField.add(btnSend, BorderLayout.EAST);
        bottomFields.add(msgField, BorderLayout.CENTER);
        add(bottomFields, BorderLayout.SOUTH);

        msgLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(msgLog);
        add(scrollPane);
        
        msg.setEnabled(false);

        loginConnection = false;

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverWindow.isServerWorking()) {
                    topFields.setVisible(false);
                    msg.setEnabled(true);
                    btnSend.setEnabled(true);
                    msgLog.append("Successful user connection\n");
                    loginConnection = true;
                    serverWindow.connectUser((String) login_list.getSelectedItem());
                    serverWindow.registerClient(ClientGUI.this);
                } else {
                    msgLog.append("Server is not running\n");
                }
            }
        });

        ActionListener sendActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
                sendMessage();
            }
        };
        msg.addActionListener(sendActionListener);
        btnSend.addActionListener(sendActionListener);

        setVisible(true);
    }

    public void logMessage(String msg) {
        msgLog.append(msg + "\n");
    }

    ///////////////////////////////////// Пробую связать ClientGUI с Server

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    public void connectToServer(String host, int port) {
        try {
            socket = new Socket(host, port); // Подключаемся к серверу
            out = new PrintWriter(socket.getOutputStream(), true); // Поток для отправки данных серверу
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Поток для чтения данных от сервера

            // Пример отправки сообщения серверу
            out.println("Пример сообщения от клиента GUI");

            // Пример чтения ответа от сервера
            String serverMessage = in.readLine();
            System.out.println("Ответ от сервера: " + serverMessage);

            socket.close();
        } catch (IOException e) {
            System.err.println("Ошибка клиентского GUI: " + e.getMessage());
        }
    }

//    public static void main(String[] args) {
//        ClientGUI client = new ClientGUI(new ServerWindow());
//        client.connectToServer("localhost", 1400);
//    }

}
