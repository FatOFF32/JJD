package Streams;

import Lessons7andAbove.Task8;

import java.io.*;

public class FileCopy {

    public static void main(String[] args) throws IOException {

        File fileIn = new File("D:\\Учеба JAVA\\ДЗ\\wp\\wp.txt");
        File fileOut = new File("D:\\Учеба JAVA\\ДЗ\\wp\\wp1.txt");

        FileCopy.copy(fileIn, fileOut);

    }

    public static void copy(File fileIn, File fileOut) throws IOException {

        try(InputStream in = new FileInputStream(fileIn);
            OutputStream out = new FileOutputStream(fileOut)){

            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0)
                out.write(buf, 0, len);
        }

    }

    private static void copyParts(File fileIn, File catalog) throws IOException {

        if (!catalog.isDirectory()){
            System.out.println("Второй параметр должен быть каталогом!");
            return;
        }

        if (!catalog.exists() && !catalog.mkdir()){
            System.out.println("Ошибка создания каталога!");
            return;
        }

        int i = 1;
        String ext = Task8.getFileExtension(fileIn);
        File fileOut = new File(catalog.getName(), "Part_" + i + "." + ext);

        try(InputStream in = new FileInputStream(fileIn);
            OutputStream out = new FileOutputStream(fileOut)){

            byte[] buf = new byte[1024];
            int len;
            int size = 0;

            while ((len = in.read(buf)) > 0){
                out.write(buf, 0, len);
                size++;
                if (size == 10){
                    size = 0;
                    i++;
                    fileOut = new File(catalog.getName(), "Part_" + i + "." + ext);
                    //out = new FileOutputStream(fileOut);
                }
            }



        }



    }

//    void createParts()
}
