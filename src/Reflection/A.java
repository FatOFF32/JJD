package Reflection;

public class A {
    private String str;

    @Resource(Impl.class)
    private I i;

    public I getI() {
        return i;
    }

    @Override
    public String toString() {
        return "A{" +
                "str='" + str + '\'' +
                ", i=" + i +
                '}';
    }
}
