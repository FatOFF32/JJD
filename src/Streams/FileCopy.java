package Streams;

import java.io.*;

public class FileCopy {

    public static void main(String[] args) throws IOException {

        File fileIn = new File("D:\\Учеба JAVA\\ДЗ\\wp\\wp.txt");
        File fileOut = new File("D:\\Учеба JAVA\\ДЗ\\wp\\wp1.txt");

        FileCopy.copyFale(fileIn, fileOut);

    }

    public static void copyFale(File fileIn, File fileOut) throws IOException {

        try(InputStream in = new FileInputStream(fileIn);
            OutputStream out = new FileOutputStream(fileOut)){

            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0);
                out.write(buf, 0, len);
        }

    }
}
