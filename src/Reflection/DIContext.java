package Reflection;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DIContext {

    private final Map<Class<?>, Object> instances = new HashMap<>();
    private static final DIContext INSTANCE = new DIContext();

    private DIContext() {
    }

    public static DIContext instance(){
        return INSTANCE;
    }

    <T> T getObject(String className) throws ClassNotFoundException{

        Class<T> clazz = (Class<T>) Class.forName(className);
        T obj = null;

        try {
            obj = getInstance(clazz, false);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;//(T) obj;
    }

    public void inject(Object o) throws InstantiationException, IllegalAccessException {
        Class<?> clazz = o.getClass();

        for (Field field : clazz.getDeclaredFields()){

            Resource resource = field.getAnnotation(Resource.class);
            if(resource == null) continue;

            Class<?> type = resource.value() == Object.class ? field.getType() : resource.value();

            field.setAccessible(true);

            field.set(o, getInstance(type, resource.singltone()));

        }

    }

    <T> T getInstance(Class<T> type, boolean singltone) throws IllegalAccessException, InstantiationException {

        T resouse;

        if (singltone){
            if (instances.containsKey(type))
                return (T) instances.get(type);
            resouse = instances.containsKey(type) ? (T) instances.get(type) : type.newInstance();
            instances.putIfAbsent(type, resouse);
        }
        else resouse = type.newInstance();

        inject(resouse);

        return resouse;

    }

}
