package ru.org.opslab.common.utils.logging;

/**
 * Заглушка для отключения логирования
 */
public class NoLog extends Log {

    public NoLog() {
    }

    @Override
    public void fatal(String msg) {
    }

    @Override
    public void error(String msg) {
    }

    @Override
    public void warn(String msg) {
    }

    @Override
    public void info(String msg) {
    }

    @Override
    public void debug(String msg) {
    }

    @Override
    public void flood(String msg) {
    }

    @Override
    public void error(String msg, Exception e) {
    }

    @Override
    public void fatal(String msg, Exception e) {
    }

}
