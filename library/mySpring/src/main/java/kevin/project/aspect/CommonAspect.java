package kevin.project.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommonAspect {

    private Logger logger = LogManager.getLogger();

    @Before(value = "within(kevin.project.controller.*Controller)")
    public void aspect(JoinPoint joinPoint) throws Throwable {
        logger.info("before aspect {}",joinPoint.getSignature());
    }
}
