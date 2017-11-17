package com.itmo.iostreams.serial.print;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

public class ServerTime implements Command{
    Date date;
    String name;

    @Override
    public String toString() {
        return "ServerTime{" +
                "date=" + date +
                '}';
    }

    @Override
    public void apply() {
        date = new Date();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(date);
        out.writeObject(name);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        date = (Date) in.readObject();
        name = (String) in.readObject();
    }
}
