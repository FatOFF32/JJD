package Reflection;

public class B {
    private int val;

    @Override
    public String toString() {
        return "B{" +
                "val=" + val +
                ", aVal=" + aVal +
                '}';
    }

    @Resource
    private A aVal;

    public I getI(){
        return aVal.getI();
    }
}
