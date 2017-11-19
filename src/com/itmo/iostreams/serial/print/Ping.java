package com.itmo.iostreams.serial.print;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Ping implements Command{
    //int ping;
    String user;

    public Ping(String user) {
        this.user = user;
    }

    @Override
    public void apply(PrintServer ps) {

    }

    @Override
    public String getSender() {
        return user;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
