package kevin.project.cache.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @ClassName ControllerAop
 * @Description TODO
 * @Date 12/31/25
 **/
@Aspect
@Component
@Slf4j
public class ControllerAop {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerAspect(){}

    @Around("controllerAspect()")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        log.info("controllerAspect " + pjp.getSignature().getName());
        return pjp.proceed();
    }
}
