package com.itmo.iostreams.serial.print;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

public class ServerTime implements Command{
    private Date date;
    private String user;

    public ServerTime(String user) {
        this.user = user;
    }

    public ServerTime() {
    }

    @Override
    public String getSender() {
        return user;
    }

    @Override
    public String toString() {
        return "ServerTime{" +
                "date=" + date +
                '}';
    }

    @Override
    public void apply(PrintServer ps) {
        date = new Date();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(date);
        out.writeObject(user);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        date = (Date) in.readObject();
        user = (String) in.readObject();
    }
}
