package com.itmo.iostreams.serial.print;

import Lessons7andAbove.ArrayList;
import Lessons7andAbove.List;
import collections.inner.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by xmitya on 28.08.16.
 */
public class PrintServer {

    private int port;

    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");

    private Collection<User> users = new HashSet<>();

    public PrintServer(int port) {
        this.port = port;
    }

    private void start() throws IOException {
        try (ServerSocket ssocket = new ServerSocket(port)) {
            System.out.println("Server started on " + ssocket);

            while (true) {
                Socket sock = ssocket.accept();

                try {
                    process(sock);
                }
                catch (ClassNotFoundException e) {
                    System.err.println("Wrong message was received");

                    e.printStackTrace();
                }
                finally {
                    sock.close();
                }
            }
        }
    }

    private void process(Socket sock) throws IOException, ClassNotFoundException {
        String host = sock.getInetAddress().getHostAddress();

        try (ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream());
             OutputStream out = sock.getOutputStream()) {
            Object obj = objIn.readObject();

            if (obj instanceof Command){ // Проверить будет ли работать, т.к. obj это у нас объект
                Command cmd = (Command) obj;
                cmd.apply();
                ObjectOutputStream objOut = new ObjectOutputStream(out);
                objOut.writeObject(cmd);
            }
            else printMessage((Message) obj, host);
        }
        catch (IOException | ClassNotFoundException | RuntimeException e) {
            System.err.println("Failed process connection from: " + host);

            e.printStackTrace();

            throw e;
        }
    }

    private void printMessage(Message msg, String senderAddr) {
        System.out.printf("%s (%s) at %s wrote: %s\n", msg.getSender(), senderAddr, format.format(new Date(msg.getTimestamp())), msg.getText());
    }

    public static void main(String[] args) throws IOException {
        if (args == null || args.length == 0)
            throw new IllegalArgumentException("Port must be specified");

        int port = Integer.parseInt(args[0]);

        PrintServer printServer = new PrintServer(port);

        printServer.start();
    }
}
