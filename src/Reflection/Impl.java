package Reflection;

@Resource() // можно писать так: @Resource(singltone = false)
public class Impl implements I {
    @Override
    public String getValue() {
        return String.valueOf(Math.random());
    }
}
