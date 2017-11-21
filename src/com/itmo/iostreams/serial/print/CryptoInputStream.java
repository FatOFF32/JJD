package com.itmo.iostreams.serial.print;

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

        byte[] byteCrypto = b.clone();
//        byte[] byteCrypto = new byte[b.length];
//        for (int i = 0; i < b.length; i++) {
//
//        }
        for (byte bCr : byteCrypto) {
            if (currByteIdx >= pass.length)
                currByteIdx = 0;
            bCr ^= pass[currByteIdx++];
        }

        return super.read(byteCrypto, off, len);
    }

    @Override
    public int read() throws IOException {
        int result = super.read();
        if (result == -1)
            return -1;
        if (currByteIdx >= pass.length)
            currByteIdx = 0;
        return super.read() ^ pass[currByteIdx++];
    }

    protected CryptoInputStream(InputStream in, byte[] pass) {
        super(in);
        this.pass = pass;
    }
}
