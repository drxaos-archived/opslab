package ru.org.opslab.common.formats.internal;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.kohsuke.graphviz.Graph;

import ru.org.opslab.common.dot.DotWriter;
import ru.org.opslab.common.dot.IndentingDotWriter;
import ru.org.opslab.common.formats.graphnode.GraphNode;
import ru.org.opslab.common.utils.logging.Log;
import ru.org.opslab.common.xml.IndentingXmlWriter;
import ru.org.opslab.common.xml.XmlWriter;

abstract public class InternalFormat {

    /**
     * Сохранить xml-дерево в файл.
     * 
     * @param fileName
     *            Имя файла
     * @param paramSequence
     *            Предпочитаемая последовательность атрибутов
     * @param graph
     *            Корень xml-дерева
     */
    public void makeXml(String fileName, String[] paramSequence, GraphNode graph) {
        OutputStream out;

        try {
            out = new FileOutputStream(fileName);
            XmlWriter xmlWriter = new IndentingXmlWriter();
            if (paramSequence != null) {
                xmlWriter.setAttributesOrder(paramSequence);
            }
            xmlWriter.writeXml(graph, out);
            out.close();
        } catch (Exception e) {
            Log.getLogger().error("Cannot write xml file: " + e, e);
        }
    }

    /**
     * Сохранить dot-граф в файл.
     * 
     * @param fileName
     *            Имя файла
     * @param graph
     *            Граф
     */
    public void makeDot(String fileName, Graph graph) {
        OutputStream out;

        try {
            out = new FileOutputStream(fileName);
            DotWriter dotWriter = new IndentingDotWriter();

            dotWriter.serialize(out, graph);
            out.close();
        } catch (Exception e) {
            Log.getLogger().error("Cannot write dot file: " + e, e);
        }

    }

}
