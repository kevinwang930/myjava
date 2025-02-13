package kevin.project.annotation;

import java.lang.annotation.Annotation;
import java.util.Arrays;




@MyAnnotation
public class AnnotationLearn {

    public static void annotationLearn() {
        Annotation[] annotations = AnnotationLearn.class.getAnnotations();
        System.out.println(annotations.length);
        System.out.println(Arrays.asList(annotations));
        System.out.println(annotations[0].annotationType());
        annotations = annotations[0].annotationType().getAnnotations();
        System.out.println(Arrays.asList(annotations));

        Annotation[] declaredAnnotations = AnnotationLearn.class.getDeclaredAnnotations();
        System.out.println(declaredAnnotations.length);
        System.out.println(Arrays.asList(declaredAnnotations));

    }

    public static void nestedAnnotationLearn() {
        Annotation[] annotations = NestedClass.class.getAnnotations();
        System.out.println(annotations.length);
        System.out.println(Arrays.asList(annotations));
        System.out.println(annotations[0].annotationType());
        annotations = annotations[0].annotationType().getAnnotations();
        System.out.println(Arrays.asList(annotations));
    }

    public static void main(String[] args) {
//        annotationLearn();
        nestedAnnotationLearn();
    }
}
