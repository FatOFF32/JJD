package Reflection;

import java.util.HashMap;
import java.util.Map;

public class DIContext {

    private final Map<Class<?>, Object> instances = new HashMap<>();
    private static final DIContext INSTANCE = new DIContext();

    private DIContext() {
    }

    public DIContext Instance(){
        return INSTANCE;
    }

    <T> T getObject(String className) throws ClassNotFoundException{

        Class<?> clazz = Class.forName(className);
        Object obj = null;

        try {
            obj = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T) obj;
    }

    public void inject(Object o){
        Class<?> clazz = o.getClass();



    }

}
