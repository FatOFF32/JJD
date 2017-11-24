package Reflection;

public class B {
    private int val;

    @Resource
    private A aVal;

    public I getI(){
        return aVal.getI();
    }
}
