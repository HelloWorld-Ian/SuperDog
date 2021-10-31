package Core.Annotation;


import java.lang.annotation.*;

/**
 * deal with webSocket msg
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebSocket {
    String value() default "";
}
