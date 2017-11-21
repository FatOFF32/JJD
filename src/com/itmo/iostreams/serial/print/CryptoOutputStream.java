package com.itmo.iostreams.serial.print;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CryptoOutputStream extends FilterOutputStream{

    byte[] pass;
    byte currByteIdx = 0;

    public CryptoOutputStream(OutputStream out, byte[] pass) {
        super(out);
        this.pass = pass;
    }

    @Override
    public void write(int b) throws IOException {
        if (currByteIdx >= pass.length)
            currByteIdx = 0;
        super.write(b ^ pass[currByteIdx++]);
    }

    @Override
    public void write(byte[] b) throws IOException {
        byte[] byteCrypto = b.clone();
        for (byte bCr : byteCrypto){
            if (currByteIdx >= pass.length)
                currByteIdx = 0;
            bCr ^= pass[currByteIdx++];
        }
        super.write(byteCrypto);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        byte[] byteCrypto = b.clone();
        for (byte bCr : byteCrypto){
            if (currByteIdx >= pass.length)
                currByteIdx = 0;
            bCr ^= pass[currByteIdx++];
        }
        super.write(byteCrypto, off, len);
    }
}
