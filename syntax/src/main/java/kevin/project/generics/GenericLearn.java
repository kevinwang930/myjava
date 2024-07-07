package kevin.project.generics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * @version 1.0
 * @ClassName GenericLearn
 * @Description TODO
 * @Date 6/28/24
 **/


class Generic<T> {

    void getTypeLearn() {
        Type superClass = getClass().getGenericSuperclass();
        System.out.println(superClass);
        System.out.println(this.getClass().getTypeParameters());

        if (superClass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superClass;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                System.out.println(actualTypeArgument);
            }
        }

        TypeVariable<? extends Class<? extends Generic>>[] typeParameters = getClass().getTypeParameters();
        for (TypeVariable<? extends Class<? extends Generic>> typeParameter : typeParameters) {
            System.out.println(typeParameter);
        }



    }
}


public class GenericLearn {
    public static void genericType() {
        Generic<String> g = new Generic<String>();
        g.getTypeLearn();
    }

    public static void main(String[] args) {
        genericType();
    }
}
