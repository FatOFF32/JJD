package com.itmo.iostreams.serial.print;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CryptoInputStream extends FilterInputStream {

    byte[] pass;
    byte currByteIdx;

    @Override
    public int read() throws IOException {
        int result = super.read();
        if (result == -1)
            return -1;
        if (currByteIdx > pass.length)
            currByteIdx = 0;
        return super.read() ^ pass[currByteIdx++];
    }

    protected CryptoInputStream(InputStream in, byte[] pass) {
        super(in);
        this.pass = pass;
    }
}
