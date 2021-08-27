package Core.annotation;


import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SingletonObjHandlerPackage {
    String[] value() default {};
}
