package com.itmo.iostreams.serial.print;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Ping implements Command{
    //int ping;
    @Override
    public void apply() {

    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
