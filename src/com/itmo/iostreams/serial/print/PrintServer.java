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
public class PrintServer implements Serializable {

    private int port;

    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");

    private HashSet<String> users = new HashSet<>();

    public HashSet<String> getUsers() {
        return users;
    }

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

        try (ObjectOutputStream objOut = new ObjectOutputStream(sock.getOutputStream());
             ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream())) {
            Object obj = objIn.readObject();

            System.out.println("Received " + obj);

            if (obj instanceof Command){ // Проверить будет ли работать, т.к. obj это у нас объект
                Command cmd = (Command) obj;
                users.add(cmd.getSender());
                cmd.apply(this);
//                ObjectOutputStream objOut = new ObjectOutputStream(out);
                objOut.writeObject(cmd);
                objOut.flush();

                System.out.println("Sent " + cmd);
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
        users.add(msg.getSender());
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
