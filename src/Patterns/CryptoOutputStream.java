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

    // Можно переопределить только этот метод, т.к. все остальные методы (write(byte[] b), write(byte[] b, int off, int len)
    // будут вызывать в итоге этот метод. Если встак переопределям эти методы, то для того чтобы наш конечный
    // переопределенный метод write(int b) не вызывался, делаем вызовы методов класса (FilterOutputStream) через переменную out.
    @Override
    public void write(int b) throws IOException {
        if (currByteIdx >= pass.length)
            currByteIdx = 0;
        // ! Тут была ошибка! необходимо вызывать методы не супер класса, а напрямую через переменую,
        //  т.к. вызов метода суперкласса возвращает внутри себя вызывает наш переопределенный метод,
        //  что приводит к двойному шифрованию. поэтому вызовим метод через out
        //super.write((byte) b ^ pass[currByteIdx++]);
        out.write((byte) b ^ pass[currByteIdx++]);
    }

    @Override
    public void write(byte[] b) throws IOException {
        byte[] byteCrypto = b.clone();
        for (int i = 0; i < byteCrypto.length; i++) {
            if (currByteIdx >= pass.length)
                currByteIdx = 0;
            byteCrypto[i] ^= pass[currByteIdx++];
        }
        out.write(byteCrypto);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        byte[] byteCrypto = b.clone();
        for (int i = off; i < off + len; i++) { //!!! Ошибка была тут!  for (int i = 0; i < byteCrypto.length; i++)
            if (currByteIdx >= pass.length)
                currByteIdx = 0;
            byteCrypto[i] ^= pass[currByteIdx++];
        }
        out.write(byteCrypto, off, len);
    }
}
