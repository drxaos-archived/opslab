package ru.org.opslab.common.xml;

import java.io.OutputStream;

import ru.org.opslab.common.formats.graphnode.GraphNode;

public interface XmlWriter {

    /**
     * Устанавливает порядок следования атрибутов в тегах.<br>
     * Атрибуты, не перечисленные в <b>names</b>, добавляются в алфавитном порядке после перечисленных.
     * 
     * @param names
     *            Массив упорядоченных имен атрибутов.
     */
    public abstract void setAttributesOrder(String[] names);

    /**
     * Сериализует заданное дерево и выводит результат в поток вывода. В начале документа записывается xml-заголовок.
     * 
     * @param node
     *            Корневой узел дерева
     * @param out
     *            Поток вывода
     * @throws Exception
     *             исключения
     */
    public abstract void writeXml(GraphNode node, OutputStream out) throws Exception;

    /**
     * Сериализует заданное дерево и выводит результат в поток вывода.
     * 
     * @param node
     *            Корневой узел дерева
     * @param out
     *            Поток вывода
     * @param head
     *            Вывод xml-заголовка в начале документа
     * @throws Exception
     *             исключения
     */
    public abstract void writeXml(GraphNode node, OutputStream out, boolean head) throws Exception;
}
