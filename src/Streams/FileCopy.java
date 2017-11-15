package Streams;

import Lessons7andAbove.Task8;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class FileCopy {

    public static void main(String[] args) throws IOException {

//        File fileIn = new File("D:\\Учеба JAVA\\ДЗ\\wp\\wp.txt");
//        File fileOut = new File("D:\\Учеба JAVA\\ДЗ\\wp\\wp1.txt");

//        FileCopy.copy(fileIn, fileOut);

//        File fileIn = new File("D:\\Java\\Лекии_Задания\\wp\\wp.txt");
//        File fileOut = new File("D:\\Java\\Лекии_Задания\\wp\\Parts");
//        FileCopy.copyParts(fileIn, fileOut);

        File fileIn = new File("D:\\Java\\Лекии_Задания\\wp\\Parts");
        File fileOut = new File("D:\\Java\\Лекии_Задания\\wp\\Parts\\full.txt");
        FileCopy.combintParts(fileIn, fileOut);

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

    public static void copyParts(File fileIn, File catalog) throws IOException {

//        if (!catalog.isDirectory()){
//            System.out.println("Второй параметр должен быть каталогом!");
//            return;
//        }

        if (!catalog.exists() && !catalog.mkdir()){
            System.out.println("Ошибка создания каталога!");
            return;
        }

        int i = 1;
        String ext = Task8.getFileExtension(fileIn);
        File fileOut;

        try(InputStream in = new FileInputStream(fileIn)){

            byte[] buf = new byte[1024];
            int len;
            int size = 0;
            boolean noComplet = (len = in.read(buf)) > 0;

            while (noComplet){

                fileOut = new File(catalog.getPath(), "Part_" + i + "." + ext);
                try(OutputStream out = new FileOutputStream(fileOut)){

                    while (noComplet){
                        out.write(buf, 0, len);
                        noComplet = (len = in.read(buf)) > 0;
                        size++;
                        if (size == 100){
                            size = 0;
                            i++;
                            break;
                        }

                    }

                }

            }

        }



    }

    public static void combintParts(File catalog, File fileOut) throws IOException {

        File[] arrayFile = catalog.listFiles();
        Arrays.sort(arrayFile, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.getName().length() != o2.getName().length())
                    return o1.getName().length() - o2.getName().length();
                return o1.compareTo(o2);
            }
        });

        int len;
        byte[] buf = new byte[1024];

        try(OutputStream out = new FileOutputStream(fileOut)){

            for (File file : arrayFile) {

                try(InputStream in = new FileInputStream(file)){
                    while ((len = in.read(buf)) > 0 ){
                        out.write(buf, 0, len);
                    }
                }
            }
        }
    }

//    void createParts()
}
