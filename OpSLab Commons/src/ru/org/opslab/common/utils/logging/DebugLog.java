package ru.org.opslab.common.utils.logging;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Отладочный логгер для гибкого управления настройками.
 */
public class DebugLog extends Log {

    private Logger logger = null;

    public DebugLog(String className) {
        if (className != null) {
            logger = Logger.getLogger(className);
        } else {
            logger = Logger.getRootLogger();
        }
    }

    @Override
    public void fatal(String msg) {
        fatal(msg, null);
    }

    @Override
    public void error(String msg) {
        error(msg, null);
    }

    @Override
    public void warn(String msg) {
        Level l;
        if ((l = logger.getLevel()) == null || l.isGreaterOrEqual(Level.WARN)) {
            logger.warn(msg + " - at " + getDebugInfo());
        }
    }

    @Override
    public void info(String msg) {
        Level l;
        if ((l = logger.getLevel()) == null || l.isGreaterOrEqual(Level.INFO)) {
            logger.info(msg + " - at " + getDebugInfo());
        }
    }

    @Override
    public void debug(String msg) {
        Level l;
        if ((l = logger.getLevel()) == null || l.isGreaterOrEqual(Level.DEBUG)) {
            logger.debug(msg + " - at " + getDebugInfo());
        }
    }

    @Override
    public void flood(String msg) {
        if (Log.verbose) {
            debug(msg);
        }
    }

    private String getDebugInfo() {
        //caller
        StackTraceElement[] ste = new Exception().getStackTrace();

        for (StackTraceElement stackTraceElement : ste) {
            if (!stackTraceElement.getClassName().equals(this.getClass().getName())) {
                return stackTraceElement.toString();
            }
        }
        return "unknown";
    }

    public String getStackTrace(Throwable e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return sw.toString();
        } catch (Exception e2) {
            return "cannot get stack";
        }
    }

    @Override
    public void error(String msg, Exception e) {
        Level l;
        if ((l = logger.getLevel()) == null || l.isGreaterOrEqual(Level.ERROR)) {
            logger.error(msg + " at " + getDebugInfo());
            if (e != null) {
                logger.error("Thrown: " + e + "\r\n" + getStackTrace(e));
            }
            Throwable ex = e;
            while (ex != null) {
                logger.error("Cause: " + ex + "\r\n" + getStackTrace(ex));
                ex = ex.getCause();
            }
        }
    }

    @Override
    public void fatal(String msg, Exception e) {
        Level l;
        if ((l = logger.getLevel()) == null || l.isGreaterOrEqual(Level.FATAL)) {
            logger.fatal(msg + " at " + getDebugInfo());
            if (e != null) {
                logger.fatal("Thrown: " + e + "\r\n" + getStackTrace(e));
            }
            Throwable ex = e;
            while (ex != null) {
                logger.fatal("Cause: " + ex + "\r\n" + getStackTrace(ex));
                ex = ex.getCause();
            }
        }
    }

}
