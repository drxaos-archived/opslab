package ru.org.opslab.common.utils.logging;

import org.apache.log4j.Logger;

/**
 * Быстрый логгер с единственной настройкой для всех классов
 */
public class FastLog extends Log {

    private Logger logger = null;

    public FastLog() {
        logger = Logger.getRootLogger();
    }

    @Override
    public void fatal(String msg) {
        logger.fatal(msg);
    }

    @Override
    public void error(String msg) {
        logger.error(msg);
    }

    @Override
    public void warn(String msg) {
        logger.warn(msg);
    }

    @Override
    public void info(String msg) {
        logger.info(msg);
    }

    @Override
    public void debug(String msg) {
        logger.debug(msg);
    }

    @Override
    public void flood(String msg) {
        if (Log.verbose) {
            debug(msg);
        }
    }

    @Override
    public void error(String msg, Exception e) {
        error(msg);
    }

    @Override
    public void fatal(String msg, Exception e) {
        fatal(msg);
    }

}
