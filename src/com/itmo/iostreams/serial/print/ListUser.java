package com.itmo.iostreams.serial.print;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ListUser implements Command{
    HashSet<String> users;
    String user;

    public ListUser(String user) {
        this.user = user;
    }

    public ListUser() {
    }

    @Override
    public String getSender() {
        return user;
    }

    @Override
    public void apply(PrintServer ps) {
        users = ps.getUsers();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(users);
        out.writeUTF(user);
    }

    @Override
    public String toString() {
        return "ListUser{" +
                "users=" + users +
                '}';
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        users = (HashSet<String>) in.readObject();
        user = in.readUTF();
    }
}
