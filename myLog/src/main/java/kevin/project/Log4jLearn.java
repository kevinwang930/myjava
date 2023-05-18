package kevin.project;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;

public class Log4jLearn {

    public void basicLog(int n) {
        Logger logger1 = LogManager.getLogger();
        logger1.info("this is logger  " + n);
    }

    public void customConfig() {
        final LoggerContext context = (LoggerContext) LogManager.getContext(false);
        final Configuration configuration = context.getConfiguration();
        // Get the existing configuration

        final Configuration config = context.getConfiguration();
        final Layout layout = PatternLayout.createDefaultLayout(config);
        Appender appender = FileAppender.createAppender("target/test.log", "false", "false", "File", "true",
                "false", "false", "4000", layout, null, "false", null, config);
        appender.start();
        config.addAppender(appender);
        AppenderRef ref = AppenderRef.createAppenderRef("File", null, null);
        AppenderRef[] refs = new AppenderRef[]{ref};
        LoggerConfig loggerConfig = LoggerConfig.createLogger(true, Level.INFO, "test",
                "true", refs, null, config, null);
        loggerConfig.addAppender(appender, null, null);
        config.addLogger("test", loggerConfig);
        context.updateLoggers();
        // Get the root logger and log a message
        Logger logger = context.getLogger("test");
        logger.info("Log message");

    }


    public static void main(String[] args) {
        Log4jLearn log4jLearn = new Log4jLearn();
        log4jLearn.basicLog(1);
        log4jLearn.customConfig();
        log4jLearn.basicLog(2);
    }
}
