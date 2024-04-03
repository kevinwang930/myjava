package kevin.project.dynamicProxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicInvocationHandler implements InvocationHandler {

    private static Logger LOGGER = LogManager.getLogger(
            DynamicInvocationHandler.class);

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        LOGGER.info("Invoked method: {} {}", method.getName(),method.getReturnType());
        Class<?> returnType = method.getReturnType();

        if (returnType.equals(String.class)) {
            return "proxy";
        }
        return 42;
    }
}