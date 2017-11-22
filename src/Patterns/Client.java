package Patterns;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;
import java.util.concurrent.ScheduledFuture;

public class Client {
    String pass;

    public static void main(String[] args) throws IOException {
        new Client().start();
    }
    public void start() throws IOException {

        try(Socket sock = new Socket()){
            sock.connect(new InetSocketAddress("localhost", 12345));

            System.out.println("Введите пароль для шифровки данных");
            Scanner scr = new Scanner(System.in);

            pass = scr.nextLine();

            try(OutputStream out = new CryptoOutputStream(sock.getOutputStream(), pass.getBytes())) {

                ObjectOutputStream objOut = new ObjectOutputStream(out);
                objOut.writeObject("Привет чувааак!");
                objOut.flush();

            }

        }

    }
}
