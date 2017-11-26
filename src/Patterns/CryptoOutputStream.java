package Patterns;

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

    @Override // Метод нормально работает, но нужно переодпеделять
    public void write(int b) throws IOException {
        if (currByteIdx >= pass.length)
            currByteIdx = 0;
        super.write((byte) b ^ pass[currByteIdx++]);
    }

    @Override
    public void write(byte[] b) throws IOException {
        byte[] byteCrypto = b.clone();
        for (int i = 0; i < byteCrypto.length; i++) {
            if (currByteIdx >= pass.length)
                currByteIdx = 0;
            byteCrypto[i] ^= pass[currByteIdx++];
        }
        super.write(byteCrypto);
    }

    // Метод нормально работает, но нужно переодпеделять либо write(int b) либо этот,
    // т.к. метод super.write(byteCrypto, off, len); вызывает метод write(int b), и потоки шифруются дважды.
//    @Override
//    public void write(byte[] b, int off, int len) throws IOException {
//        byte[] byteCrypto = b.clone();
//        for (int i = off; i < off + len; i++) { //!!! Ошибка была тут!  for (int i = 0; i < byteCrypto.length; i++)
//            if (currByteIdx >= pass.length)
//                currByteIdx = 0;
//            byteCrypto[i] ^= pass[currByteIdx++];
//        }
//        super.write(byteCrypto, off, len);
//    }
}
