package Reflection;

public class Impl implements I {
    @Override
    public String getValue() {
        return String.valueOf(Math.random());
    }
}
