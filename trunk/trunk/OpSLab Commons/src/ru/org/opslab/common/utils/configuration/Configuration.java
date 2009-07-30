package ru.org.opslab.common.utils.configuration;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * Класс для работы с конфигурационными файлами. Класс включает самый базовый набор возможностей для работы с различными типами содержимого. При необходимости дополнительных модификаций отписываться в
 * ветку багтрекинга.
 */
public abstract class Configuration {
    /**
     * Набор конфигов. Для использования более чем одного конфига в программе.
     */
    private static Map<String, Configuration> _configs;

    /**
     * Получение конфига по умолчанию. Рекомендуется использовать если программа использует не более одного конфига.
     * 
     * @return Конфиг по умолчанию.
     */
    public static Configuration getConfig() {
        return getConfig("");
    }

    /**
     * Получение конфига по ключевому имени.
     * 
     * @param keyName
     *            Ключевое имя, заданное конфигу при загрузке
     * @return Конфиг, связанный с заданным ключем. Если такого нет, или список пуст - null.
     */
    public static Configuration getConfig(String keyName) {
        if (_configs != null) {
            return _configs.get(keyName);
        } else {
            return null;
        }
    }

    public static void loadConfig(Loader loader) {
        loadConfig("", loader);
    }

    public static void loadConfig(String keyName, Loader loader) {
        if (_configs == null) {
            _configs = new TreeMap<String, Configuration>();
        }
        _configs.put(keyName, loader.getLoadedConfig());
    }

    /**
     * Получение строкового параметра, ассоциированного с заданным ключом.
     * 
     * @param key
     *            Конфигурационный ключ.
     * @return Строка, ассоциированная с ключом.
     */
    abstract public String getString(String key);

    /**
     * Получение строкового параметра, ассоциированного с заданным ключом.
     * 
     * @param key
     *            Конфигурационный ключ.
     * @param defaultValue
     *            Значение по умолчанию.
     * @return Строка, ассоциированная с ключом.
     */
    abstract public String getString(String key, String defaultValue);

    /**
     * Получение массива строк, ассоцированного с заданным ключом.
     * 
     * @param key
     *            Конфигурационный ключ.
     * @return Массив строк, ассоциированный с ключом.
     */
    abstract public String[] getStringArray(String key);

    /**
     * Добавление свойства в конфиг. Если свойство с таким именем уже содержится, то новое значение будет добавлено. Наприимер, если имеется resource.loader = file то после
     * addProperty("resource.loader", "classpath") значение будет ["file", "classpath"]
     * 
     * @param key
     *            Конфигурационный ключ.
     * @param value
     *            Значение добавляемого параметра.
     */
    abstract public void addProperty(String key, Object value);

    /**
     * Установка нового значения для свойства. Старое значение теряется.
     * 
     * @param key
     *            Конфигурационный ключ.
     * @param value
     *            Новое значение.
     */
    abstract public void setProperty(String key, Object value);

    /**
     * Удаление свойства из конфига.
     * 
     * @param key
     *            Конфигуационный ключ.
     */
    abstract public void clearProperty(String key);

    /**
     * Получение списка строк, ассоциированного с заданным ключом.
     * 
     * @param key
     *            Конфигурационный ключ.
     * @return Список строк, ассоциированный с ключом, null усли такого соответствия нет.
     */
    abstract public List<String> getList(String key);

    /**
     * Получение логического параметра, ассоциированного с заданным ключом.
     * 
     * @param key
     *            Конфигурационный ключ.
     * @return boolean, ассоциированный с ключом.
     */
    abstract public boolean getBoolean(String key);

    /**
     * Получение логического параметра, ассоциированного с заданным ключом.
     * 
     * @param key
     *            Конфигурационный ключ.
     * @param defaultValue
     *            Значение по умолчанию.
     * @return boolean, ассоциированный с ключом.
     */
    abstract public boolean getBoolean(String key, boolean defaultValue);

    /**
     * Получение целого числа, ассоциированного с заданным ключом.
     * 
     * @param key
     *            Конфигурационный ключ.
     * @return Целое число, ассоциированное с ключом.
     */
    abstract public int getInt(String key);

    /**
     * Получение целого числа, ассоциированного с заданным ключом.
     * 
     * @param key
     *            Конфигурационный ключ.
     * @param defaultValue
     *            Значение по умолчанию.
     * @return Целое число, ассоциированное с ключом.
     */
    abstract public int getInt(String key, int defaultValue);

    /**
     * Добавление к содержимому конфига содержимого другого конфига. Совпадающие ключи перезаписываются. Старые значения будут утеряны.
     * 
     * @param c
     *            Добавляемый конфиг.
     */
    abstract public void append(Configuration c);

    /**
     * Получение свойства любого типа, ассоциированного с данным ключом.
     * 
     * @param key
     *            Конфигурационный ключ.
     * @return Ассоциированное свойство любого типа.
     */
    abstract public Object getProperty(String key);

    /**
     * Получение свойства любого типа, ассоциированного с данным ключом.
     * 
     * @param key
     *            Конфигурационный ключ.
     * @param defaultValue
     *            Значение по умолчанию.
     * @return Ассоциированное свойство любого типа.
     */
    abstract public Object getProperty(String key, Object defaultValue);

    /**
     * Получение списка всех конфигурационных ключей для заданного конфига, которые начинаются с заданного префикса.
     * 
     * @param prefix
     *            Префикс для конфигурационных ключей.
     * @return Список конфигурационных ключей с заданным префиксом.
     */
    abstract public List<String> getKeys(String prefix);

    /**
     * Получение списка всех конфигурационных ключей для заданного конфига.
     * 
     * @return Список всех конфигурационных ключей.
     */
    abstract public List<String> getKeys();

    /**
     * Проверка на существование ключа в загруженном конфиге.
     * 
     * @return true - существует, false - нет.
     */
    abstract public boolean containsKey(String key);

    /**
     * Генерация объекта Properties из данного конфига.
     * 
     * @return Properties
     */
    abstract public Properties getProperties();

    /**
     * Получение строково параметра, ассоциированного со сложным ключом. Составные части сложного ключа разделяются в соответствии с типом конфига и присущем ему разделителем.
     * 
     * @param keys
     *            Составные части ключа
     * @return Строковой параметр, ассоциированный с заднным сложным ключом.
     */
    abstract public String getStringByKeySet(String... keys);
}
