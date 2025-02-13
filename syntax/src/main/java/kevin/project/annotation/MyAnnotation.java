package kevin.project.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@BaseAnnotation
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation  {
    String value() default "123";
}
