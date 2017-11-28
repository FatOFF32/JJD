package multithreading;

import java.io.*;
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
        private String fileName;
        private long sizeFile;

        private Reader(Socket socket) {
            this.socket = socket;
        }


        @Override
        public void run() {

            try(DataInputStream in = new DataInputStream(socket.getInputStream())) {

                int len;
                byte[] buffer = new byte[1024];
                fileName = in.readUTF(); // имя файла
                sizeFile = in.readLong(); // размер файла возможно будет использоваться для определения, хватит ли места или нет...

                FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\Java\\Лекии_Задания\\" + fileName));

                while ((len = in.read(buffer)) > 0){
                    fileOutputStream.write(buffer); // Если не проканает, попробовать вот так: fileOutputStream.write(buffer, 0, len);
                }

            } catch (IOException e) {
                System.out.println("Ошибка чтения файла!" + e.getMessage());
            }

        }
    }
}
