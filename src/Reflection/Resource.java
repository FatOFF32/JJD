package Reflection;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE})
//@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Resource {
    Class<?> value() default Object.class;
    boolean singltone() default true;
}
