package kevin.project;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class MyLogger {
    private static Logger logger = Logger.getLogger("MyLog");



    public static Logger getLogger() throws IOException {
        // Create a FileHandler and configure it
        Handler fileHandler = new FileHandler("mylog.log");
        fileHandler.setFormatter(new SimpleFormatter());

        // Attach the FileHandler to the logger
        logger.addHandler(fileHandler);
        return logger;


    }

    public static void main(String[] args) {
        logger.info("test");
    }
}


public class LoggerLearn {
    public static void main(String[] args) throws IOException {
        MyLogger.getLogger().severe("test");
    }
}
