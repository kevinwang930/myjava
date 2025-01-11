package kevin.project;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Slf4j
public class Slf4jLearn {

    public void simpleLog() {
        Logger logger = LoggerFactory.getLogger(Slf4jLearn.class);
        logger.error("this is slf4j logger");
    }

    public static void main(String[] args) {
        Slf4jLearn slf4jLearn = new Slf4jLearn();
        slf4jLearn.simpleLog();
        log.info("this is log4j logger");
    }
}
