package com.itmo.iostreams.serial.print;

import collections.inner.User;

import java.io.*;
import java.net.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by xmitya on 28.08.16.
 */
public class PrintClient {

    private SocketAddress serverAddr;

    private String name;

    private Scanner scanner;

    public PrintClient(SocketAddress serverAddr, Scanner scanner) {
        this.serverAddr = serverAddr;
        this.scanner = scanner;
    }

    private void start() throws IOException, ClassNotFoundException {
        System.out.println("Enter your name: ");

        name = scanner.nextLine();

        while (true) {
            System.out.println("Enter message to send: ");

            String msg = scanner.nextLine();

            if ("/exit".equals(msg))
                break;
            else if ("/nick".equals(msg)) {
                System.out.println("Enter new name:");

                name = scanner.nextLine();

                continue;
            }
            else if ("/myaddr".equals(msg)) {
                printAddresses();

                continue;
            }
            else if ("/list_user".equals(msg)) {
                printListUserToServer();

                continue;
            }
            else if ("/server_time".equals(msg)) {
                printServerTime();

                continue;
            }

            else if ("/ping".equals(msg)) {
                ping();

                continue;
            }

            buildAndSendMessage(msg);
        }
    }

    private void ping() throws IOException, ClassNotFoundException {

        try(Socket sock = new Socket()){
            sock.connect(serverAddr);

            try(OutputStream out = sock.getOutputStream();
                InputStream in = sock.getInputStream()) {

                ObjectOutputStream objOut;
                ObjectInputStream objIn;
                Ping ping = new Ping(name);
                long timeOut;
                long timeIn;
                for (int i = 0; i < 4; i++) {
                    objOut = new ObjectOutputStream(out);
                    timeIn = System.nanoTime();
                    objOut.writeObject(ping);
                    objOut.flush();

                    objIn = new ObjectInputStream(in);
                    objIn.readObject();
                    timeOut = System.nanoTime();

                    System.out.println("ping " + sock.getInetAddress() + " отклик в нано секундах " + (timeOut - timeIn));

                }

            }
        }

    }

    private void printServerTime() throws IOException, ClassNotFoundException {

        // подключились к серверу
        try(Socket sock = new Socket()){
            sock.connect(serverAddr);

            // создали входящие и исходящие потоки
            try(OutputStream out = sock.getOutputStream();
                InputStream in = sock.getInputStream()){
                //обернули в объекты для отправки и принятия
                ObjectOutputStream objOut = new ObjectOutputStream(out);
//                ObjectInputStream objIn = new ObjectInputStream(in); // Так почему то не работает...

                // наш объект, куда мы должны будем поместить дату
                ServerTime st = new ServerTime(name);

                // толкаем
                objOut.writeObject(st);
                objOut.flush();

                // ждем ответа
                ObjectInputStream objIn = new ObjectInputStream(in);
                st = (ServerTime) objIn.readObject();

                System.out.println(st);

            }
        }


    }

    private void printListUserToServer() throws IOException, ClassNotFoundException {

        // подключились к серверу
        try(Socket sock = new Socket()){
            sock.connect(serverAddr);

            // создали входящие и исходящие потоки
            try(OutputStream out = sock.getOutputStream();
                InputStream in = sock.getInputStream()){
                //обернули в объекты для отправки и принятия
                ObjectOutputStream objOut = new ObjectOutputStream(out);

                // наш объект, куда мы должны будем поместить дату
                ListUser listUser = new ListUser(name);

                // толкаем
                objOut.writeObject(listUser);
                objOut.flush();

                // ждем ответа
                ObjectInputStream objIn = new ObjectInputStream(in);
                listUser = (ListUser) objIn.readObject();

                System.out.println(listUser);

            }
        }


    }

    private void printAddresses() throws SocketException {
        Enumeration e = NetworkInterface.getNetworkInterfaces();

        while(e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();

            Enumeration ee = n.getInetAddresses();

            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();

                System.out.println(i.getHostAddress());
            }
        }
    }

    private void buildAndSendMessage(String msg) throws IOException {
        Message message = new Message(System.currentTimeMillis(), name, msg);

        sendPrintMessage(message);

        System.out.println("Sent: " + message);
    }

    private void sendPrintMessage(Message msg) throws IOException {
        try (Socket sock = new Socket()) {
            sock.connect(serverAddr);

            try (OutputStream out = sock.getOutputStream()) {
                ObjectOutputStream objOut = new ObjectOutputStream(out);

                objOut.writeObject(msg);

                objOut.flush();
            }
        }
    }

    private static SocketAddress parseAddress(String addr) {
        String[] split = addr.split(":");
        return new InetSocketAddress(split[0], Integer.parseInt(split[1]));
    }

    public static void main(String[] args) throws IOException {
        String addr = null;

        if (args != null && args.length > 0)
            addr = args[0];

        Scanner scanner = new Scanner(System.in);

        if (addr == null) {
            System.out.println("Enter server address");

            addr = scanner.nextLine();
        }

        PrintClient client = new PrintClient(parseAddress(addr), scanner);

        try {
            client.start();
        } catch (ClassNotFoundException e) {
            System.out.println("Чувак, не суй в меня больше свой объект!" + e.getMessage());
        }
    }
}
