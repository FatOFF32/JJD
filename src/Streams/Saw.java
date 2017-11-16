package Streams;

import java.io.IOException;
import java.io.InputStream;

public class Saw extends InputStream {

    int a = 0; //00000000
    int inkr = 128; // на какой знак двигаем

    public static void main(String[] args) throws IOException {

        int b;
        try(InputStream in = new Saw()){

            for (int i = 0; i < 80; i++) {
                b = in.read();
                System.out.println(String.format("%8s", Integer.toBinaryString(b)).replace(' ', '0'));
            }
        }
    }
    @Override
    public int read() throws IOException {

        // Почему то не получилась работа с байтом, сделаем через интежер
        if (a == 255) //11111111
            a = 0;

        // Смещаем биты
        a = a >> 1;
        a = a | inkr;

        return a;
    }
}