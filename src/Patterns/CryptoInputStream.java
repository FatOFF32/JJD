package Patterns;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class CryptoInputStream extends FilterInputStream {

    byte[] pass;
    byte currByteIdx = 0;

    public static void main(String[] args) {

//        int b = 1;
//        int c = 1;
//        System.out.println(b);
//        c += b;
//        System.out.println(c);
//        System.out.println(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {

        int res = super.read(b, off, len);

        if (res < 0)
            return -1;
//        byte[] byteCrypto = b.clone();
//        byte[] byteCrypto = new byte[b.length];
        for (int i = off; i < res + off; i++) { // !!! Тут была ошибка! было так: for (int i = 0; i < b.length; i++)
            if (currByteIdx >= pass.length)
                currByteIdx = 0;
            b[i] ^= pass[currByteIdx++];
        }

        return res;
    }

    @Override
    public int read() throws IOException {
        int result = super.read();
        if (result == -1)
            return -1;
        if (currByteIdx >= pass.length)
            currByteIdx = 0;
        return ((byte) result ^ pass[currByteIdx++]);// & 0xFF;
    }

    protected CryptoInputStream(InputStream in, byte[] pass) {
        super(in);
        this.pass = pass;
    }
}
