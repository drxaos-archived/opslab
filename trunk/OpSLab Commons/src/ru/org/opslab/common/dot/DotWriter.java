package ru.org.opslab.common.dot;

import java.io.OutputStream;

import org.kohsuke.graphviz.Graph;

public interface DotWriter {
    /**
     * Сериализует заданную структуру дот-файла и выводит в поток.
     * 
     * @param out
     *            Поток вывода
     * @param graph
     *            Корневой узел структуры
     * @throws Exception
     *             исключения
     */
    public void serialize(OutputStream out, Graph graph) throws Exception;

}
