package ru.org.opslab.common.xml;

import java.io.InputStream;

import ru.org.opslab.common.formats.graphnode.GraphNode;

public interface XmlReader {

    /**
     * Принимает входной поток xml данных и генерирует граф.
     * 
     * @param in
     *            Входной поток.
     * @return Граф узлов GraphNode.
     */
    public abstract GraphNode readXml(InputStream in) throws Exception;

}
