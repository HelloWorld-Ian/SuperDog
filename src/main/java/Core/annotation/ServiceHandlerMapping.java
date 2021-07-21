package Core.annotation;

import java.lang.annotation.*;

/**
 * define the request that mapping the specific service class
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceHandlerMapping {
    String value() default "";
}
