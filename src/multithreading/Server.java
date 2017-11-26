package multithreading;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    void startFTP(){
        try (ServerSocket serverSocket = new ServerSocket(12345)) {

            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new Reader(socket)).start();
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Reader implements Runnable{
        private final Socket socket;

        private Reader(Socket socket) {
            this.socket = socket;
        }


        @Override
        public void run() {
            
        }
    }
}
