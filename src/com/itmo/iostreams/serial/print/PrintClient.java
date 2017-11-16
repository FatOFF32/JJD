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

            buildAndSendMessage(msg);
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
                ObjectInputStream objIn = new ObjectInputStream(in);

                // наш объект, куда мы должны будем поместить дату
                ServerTime st = new ServerTime();

                // толкаем
                objOut.writeObject(st);
                objOut.flush();

                // ждем ответа
                st = (ServerTime) objIn.readObject();

                System.out.println(st);

            }
        }


    }

    private void printListUserToServer() throws IOException {

        // подключились к серверу
        try(Socket sock = new Socket()){
            sock.connect(serverAddr);

            // создали входящие и исходящие потоки
            try(OutputStream out = sock.getOutputStream();
                InputStream in = sock.getInputStream()){
                //обернули в объекты для отправки и принятия
                ObjectOutputStream objOut = new ObjectOutputStream(out);
                ObjectInputStream objIn = new ObjectInputStream(in);

                // наш объект, куда мы должны будем поместить дату


//                // толкаем
//                objOut.writeObject();
//                objOut.flush();
//
//                // ждем ответа
//                objIn.read();


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
            System.out.println("Чувак, не суй мне больше свой кривой объект!" + e.getMessage());
        }
    }
}
