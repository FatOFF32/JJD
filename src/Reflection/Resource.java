package Reflection;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Resource {
    Class<?> value() default Object.class;
    boolean singltone() default false;
}
