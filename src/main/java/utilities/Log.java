package utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Log {
    public static final Logger logger = Logger.getLogger(Log.class);

    public Log() {
        DOMConfigurator.configure("src\\main\\resources\\log4j.xml");
    }

    public static void startLog(String logName) {
        logger.info("-------------------------- " + logName + " ---------------------------\n");
    }

    public static void info(String parameter) {
        logger.info(parameter);
    }

    public static void debug(String parameter) {
        logger.debug(parameter);
    }

    public static void warn(String parameter) {
        logger.warn(parameter);
    }

    public static void error(String parameter) {
        logger.error(parameter);
    }

    public static void fatal(String parameter) {
        logger.fatal(parameter);
    }
}
