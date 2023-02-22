package kevin.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.LoggerConfig;

public class Log4jLearn {
    private static Logger logger = LogManager.getLogger();

    public void basicLog() {
        Logger logger1 = LogManager.getLogger();
        logger1.info("this is logger 1");
    }

    public void anotherConfig() {
        Configurator.initialize(null, "log4j2_1.xml");
        Logger logger2 = LogManager.getLogger();
        logger2.info("this is logger 2");
    }

    public void codeConfig() {
        String logPath = "application2.log";
        LoggerContext context = (LoggerContext) LogManager.getContext();
        Configuration config = context.getConfiguration();
        FileAppender appender = FileAppender.newBuilder()
                .withFileName(logPath)
                .withName("MyAppender")
                .withImmediateFlush(true)
                .withIgnoreExceptions(false)
                .setConfiguration(config)
                .build();

        appender.start();
        // Add the appender to the LoggerConfig
        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.addAppender(appender, null, null);

        // Update the configuration
        Configurator.initialize(config);

        // Log a message
        logger.info("Hello, world!");

        // Shutdown the LoggerContext
        context.stop();
    }
    public static void main(String[] args) {
        Log4jLearn log4jLearn = new Log4jLearn();
        log4jLearn.basicLog();
        log4jLearn.anotherConfig();
        log4jLearn.codeConfig();
    }
}