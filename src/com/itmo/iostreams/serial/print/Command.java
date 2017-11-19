package com.itmo.iostreams.serial.print;

import java.io.Externalizable;

public interface Command extends Externalizable{

    void apply(PrintServer ps);
    String getSender();
}
