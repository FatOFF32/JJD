package Patterns;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    String pass;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        new Server().start();

    }

    public void start() throws IOException, ClassNotFoundException {

        try(ServerSocket serverSocket = new ServerSocket(12345)){

            System.out.println("Server started on " + serverSocket);

            System.out.println("Введите пароль для дешифровки сообщений! ");
            Scanner scr = new Scanner(System.in);

            pass = scr.nextLine();

            try(Socket socket = serverSocket.accept()){ // тут ждём пока нам кто то постучится
                CryptoInputStream in = new CryptoInputStream(socket.getInputStream(), pass.getBytes());

                    ObjectInputStream obhIn = new ObjectInputStream(in);
                    Object obj = obhIn.readUTF();

                    System.out.println(obj);


            }

        }
    }
}
