package ru.org.opslab.common.utils.configuration;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationConverter;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConversionException;
import org.apache.commons.configuration.PropertiesConfiguration;

import ru.org.opslab.common.utils.logging.Log;

/**
 * Класс для работы с конфигурационными файлами properties.
 */
public class PropertiesConfig extends Configuration {
    /**
     * Разделитель в ключах конфига
     */
    private String SPLITTER = ".";
    /**
     * Класс, выполняющий всю работу. Представитель библиотеки Apache-Commons Configuration.
     */
    private PropertiesConfiguration _properties;

    /**
     * Конструктор, создающий пустой контейнер.
     */
    public PropertiesConfig() {
        _properties = new PropertiesConfiguration();
        _properties.setThrowExceptionOnMissing(true);
    }

    /**
     * Конструктор, загружающий конфиг из файла.
     * 
     * @param filename
     *            Имя файла для загрузки.
     * @throws IOException
     *             Ошибка работы с файлом.
     */
    public PropertiesConfig(String filename) throws IOException {
        try {
            _properties = new PropertiesConfiguration(filename);
            _properties.setThrowExceptionOnMissing(true);
        } catch (ConfigurationException e) {
            Log.getLogger().error("Couldn't load properties from file " + filename);
            Log.getLogger().debug("Configuration exception catch while creating instance of org.apache.commons.configuration.PropertiesConfiguration");
            throw new IOException("Error reading from file: " + filename);
        }
    }

    /**
     * Конструктор, загружающий конфиг из файла.
     * 
     * @param reader
     *            Ридер.
     * @throws IOException
     *             Ошибка работы с файлом.
     */
    public PropertiesConfig(Reader reader) throws IOException {
        try {
            _properties = new PropertiesConfiguration();
            _properties.load(reader);
            _properties.setThrowExceptionOnMissing(true);
        } catch (ConfigurationException e) {
            Log.getLogger().error("Couldn't load properties from input reader");
            Log.getLogger().debug("Configuration exception catch while loading org.apache.commons.configuration.PropertiesConfiguration from Reader");
            throw new IOException("Error reading from input reader");
        }
    }

    @Override
    public void addProperty(String key, Object value) {
        _properties.addProperty(key, value);
    }

    @Override
    public void clearProperty(String key) {
        _properties.clearProperty(key);
    }

    @Override
    public String getString(String key) {
        return _properties.getString(key);
    }

    @Override
    public String getString(String key, String defaultValue) {
        return _properties.getString(key, defaultValue);
    }

    @Override
    public String[] getStringArray(String key) {
        return _properties.getStringArray(key);
    }

    @Override
    public void setProperty(String key, Object value) {
        _properties.setProperty(key, value);
    }

    @Override
    public List<String> getList(String key) {
        // Почему то возвращается не параметризованный список
        List<?> out;
        try {
            out = _properties.getList(key);
        } catch (ConversionException e) {
            // Рантайм исключение
            return null;
        }
        List<String> ret = new ArrayList<String>();
        for (Object o : out) {
            ret.add(o.toString());
        }
        return ret;
    }

    @Override
    public boolean getBoolean(String key) {
        return _properties.getBoolean(key);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return _properties.getBoolean(key, defaultValue);
    }

    @Override
    public int getInt(String key) {
        return _properties.getInt(key);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return _properties.getInt(key, defaultValue);
    }

    @Override
    public void append(Configuration c) {
        // Получаем все ключи
        List<String> keys = c.getKeys();
        // И поочередно переписываем все значения
        for (String key : keys) {
            // set в отличие от add не добавит значение к текущему, а перепишет его 
            setProperty(key, c.getProperty(key));
        }
    }

    @Override
    public List<String> getKeys(String prefix) {
        // Оригинальный метод возвращает FilterIterator. По нему не получится организовать обход.
        Iterator<?> iterator = _properties.getKeys(prefix);
        // Преобразование итератора в более удобоиспользуемый список.
        List<String> ret = new ArrayList<String>();
        while (iterator.hasNext()) {
            ret.add(iterator.next().toString());
        }
        return ret;
    }

    @Override
    public List<String> getKeys() {
        // Оригинальный метод возвращает FilterIterator. По нему не получится организовать обход.
        Iterator<?> iterator = _properties.getKeys();
        // Преобразование итератора в более удобоиспользуемый список.
        List<String> ret = new ArrayList<String>();
        while (iterator.hasNext()) {
            ret.add(iterator.next().toString());
        }
        return ret;
    }

    @Override
    public Object getProperty(String key) {
        return _properties.getProperty(key);
    }

    @Override
    public Object getProperty(String key, Object defaultValue) {
        Object result = _properties.getProperty(key);
        if (result == null) {
            result = defaultValue;
        }
        return result;
    }

    @Override
    public Properties getProperties() {
        return ConfigurationConverter.getProperties(_properties);
    }

    @Override
    public boolean containsKey(String key) {
        return _properties.containsKey(key);
    }

    @Override
    public String getStringByKeySet(String... keys) {
        StringBuilder builder = new StringBuilder();
        for (String key : keys) {
            builder.append(key);
            builder.append(SPLITTER);
        }
        builder.deleteCharAt(builder.length() - 1);
        return _properties.getString(builder.toString());
    }

}
