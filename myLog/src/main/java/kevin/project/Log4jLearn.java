package kevin.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jLearn {

    public void logInfo() {
        Logger logger1 = LogManager.getLogger();
        logger1.info("log info");
    }
    public void logDebug() {
        Logger logger1 = LogManager.getLogger();
        logger1.debug("log debug");
    }


    public static void main(String[] args) {
        Log4jLearn log4jLearn = new Log4jLearn();
        log4jLearn.logInfo();
        log4jLearn.logDebug();

    }
}
