package ru.org.opslab.common.utils.logging;

import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.log4j.PropertyConfigurator;

import ru.org.opslab.common.utils.configuration.Configuration;

/**
 * Класс для ведения журнала работы системы.<br>
 * <a href="http://opslab.org.ru/trac/opslab/wiki/Osv/Log">Wiki:Osv/Log</a>
 */
public abstract class Log {

    /**
     * Выключить логирование
     */
    public static int LOGGING_OFF = 0;

    /**
     * Использовать только настройки rootLogger
     */
    public static int LOGGING_FAST = 1;

    /**
     * Использовать все настройки и определять логгер по стеку вызовов.
     */
    public static int LOGGING_DEBUG = 2;

    public static String LOGGING_OFF_STR = "off";
    public static String LOGGING_FAST_STR = "fast";
    public static String LOGGING_DEBUG_STR = "debug";

    public static String LOGMODE = "log4j.logmode";

    private static int mode = Log.LOGGING_FAST;

    private static Log _fastInstance = null;
    private static Log _offInstance = null;
    private static Map<String, Log> _debugMap = null;

    /**
     * Выводить "флудливые" сообщения
     */
    protected static boolean verbose = false;
    public static String CONF_VERBOSE = "log4j.verbose";

    /**
     * Возвращает экземпляр логгера, выбранный в соответствии с настройками.
     * 
     * @return объект одного из наследников класса Log
     */
    public static Log getLogger() {

        if (Log.mode == Log.LOGGING_FAST) {
            if (Log._fastInstance == null) {
                Log._fastInstance = new FastLog();
            }
            return Log._fastInstance;

        } else if (Log.mode == Log.LOGGING_DEBUG) {
            if (Log._debugMap == null) {
                Log._debugMap = new TreeMap<String, Log>();
            }
            Log l;
            String cname;
            cname = new Throwable().getStackTrace()[1].getClassName();
            if (Log._debugMap.containsKey(cname)) {
                l = Log._debugMap.get(cname);
            } else {
                l = new DebugLog(cname);
                Log._debugMap.put(cname, l);
            }
            return l;

        } else {
            if (Log._offInstance == null) {
                Log._offInstance = new NoLog();
            }
            return Log._offInstance;
        }
    }

    /**
     * Загрузить настройки логгера из хранилища Properties.
     * 
     * @param config
     *            Объект Properties
     */
    public static void loadProperties(Properties config) {
        PropertyConfigurator.configure(config);
        configureLogging(config.getProperty(LOGMODE, LOGGING_FAST_STR));
        verbose = Configuration.getConfig().getBoolean(CONF_VERBOSE, false);
    }

    /**
     * Установить тип логирования.
     * 
     * @param mode
     *            Вариант логирования:<br>
     *            LOGGING_OFF - выключить<br>
     *            LOGGING_FAST - одна настройка для всех классов<br>
     *            LOGGING_DEBUG - отдельные настройки для каждого класса
     */
    public static void configureLogging(int mode) {
        Log.mode = mode;
        if (mode != Log.LOGGING_FAST) {
            Log._fastInstance = null;
        }
        if (mode != Log.LOGGING_DEBUG) {
            Log._debugMap = null;
        }
        if (mode != Log.LOGGING_OFF) {
            Log._offInstance = null;
        }
    }

    /**
     * Установить тип логирования.
     * 
     * @param mode
     *            Вариант логирования:<br>
     *            off - LOGGING_OFF - выключить<br>
     *            fast - LOGGING_FAST - одна настройка для всех классов<br>
     *            debug - LOGGING_DEBUG - отдельные настройки для каждого класса
     */
    public static void configureLogging(String mode) {
        if (mode.equals(LOGGING_OFF_STR)) {
            Log.configureLogging(Log.LOGGING_OFF);
        } else if (mode.equals(LOGGING_FAST_STR)) {
            Log.configureLogging(Log.LOGGING_FAST);
        } else if (mode.equals(LOGGING_DEBUG_STR)) {
            Log.configureLogging(Log.LOGGING_DEBUG);
        } else {
            Log.getLogger().debug("Log mode must be [" + LOGGING_OFF_STR + ", " + LOGGING_FAST_STR + ", " + LOGGING_DEBUG_STR + "]");
        }
    }

    /**
     * Ошибка, приводящая к завершению программы
     * 
     * @param msg
     *            Сообщение для записи в журнал
     */
    abstract public void fatal(String msg);

    /**
     * Ошибка, приводящая к завершению программы
     * 
     * @param msg
     *            Сообщение для записи в журнал
     * @param e
     *            Исключение, вызвавшее запись в лог.
     */
    abstract public void fatal(String msg, Exception e);

    /**
     * Ошибка, из-за которой программа работает некорректно
     * 
     * @param msg
     *            Сообщение для записи в журнал
     */
    abstract public void error(String msg);

    /**
     * Ошибка, из-за которой программа работает некорректно
     * 
     * @param msg
     *            Сообщение для записи в журнал
     * @param e
     *            Исключение, вызвавшее запись в лог.
     */
    abstract public void error(String msg, Exception e);

    /**
     * Предупреждение о возможной некорректной работе
     * 
     * @param msg
     *            Сообщение для записи в журнал
     */
    abstract public void warn(String msg);

    /**
     * Информация о работе компонентов программы (запуск, приостановка, завершение)
     * 
     * @param msg
     *            Сообщение для записи в журнал
     */
    abstract public void info(String msg);

    /**
     * Отладочная информация о ключевых местах алгоритма
     * 
     * @param msg
     *            Сообщение для записи в журнал
     */
    abstract public void debug(String msg);

    /**
     * Отладочная информация о часто повторяющихся местах алгоритма
     * 
     * @param msg
     *            Сообщение для записи в журнал
     */
    abstract public void flood(String msg);

}
