package ru.org.opslab.common.xml;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import ru.org.opslab.common.formats.graphnode.GraphNode;
import ru.org.opslab.common.formats.graphnode.GraphNodeText;
import ru.org.opslab.common.formats.graphnode.InfoNode;
import ru.org.opslab.common.utils.logging.Log;

/**
 * Реализация XmlWriter на стандартном классе IndentingXmlStreamWriter
 */
public class PlainXmlWriter implements XmlWriter, XmlStrings {

    protected XMLStreamWriter stream;
    protected List<String> attrsOrder = new ArrayList<String>();

    /**
     * Закрывает все открытые теги.
     * 
     * @throws Exception
     *             исключения
     */
    private void endDocument() throws IOException, XMLStreamException {
        Log.getLogger().debug("Ending XML document");
        stream.writeEndDocument();
        stream.flush();
    }

    /**
     * Выводит шапку xml-документа в указанный поток.
     * 
     * @throws Exception
     *             исключения
     */
    private void startDocument() throws IOException, XMLStreamException {
        Log.getLogger().debug("Starting XML document");
        stream.writeStartDocument();
        stream.flush();
    }

    @Override
    public void writeXml(GraphNode node, OutputStream out, boolean head) throws IOException, XMLStreamException {
        Log.getLogger().info("Writing GraphNode to XML stream");

        XMLOutputFactory f = XMLOutputFactory.newInstance();
        stream = f.createXMLStreamWriter(out);
        if (node == null) {
            return;
        }
        if (head) {
            startDocument();
        }
        write(node);
        endDocument();
        stream.flush();
    }

    public void writeXml(GraphNode node, OutputStream out) throws IOException, XMLStreamException {
        writeXml(node, out, true);
    }

    private void write(GraphNode src) throws XMLStreamException {
        src.updateInfo();
        write(src.getRootInfoNode());
    }

    private void write(InfoNode src) throws XMLStreamException {
        String[] attrs;
        InfoNode[] children;

        if (src.isComment()) {
            String text = ((GraphNodeText) src.getNode()).getText();
            Log.getLogger().debug("Writing comment [" + text + "]");
            stream.writeComment(text);
            return;
        }

        if (src.isText()) {
            String text = ((GraphNodeText) src.getNode()).getText();
            Log.getLogger().debug("Writing text [" + text + "]");
            stream.writeCharacters(text);
            return;
        }

        if (src.isLink()) {
            Log.getLogger().debug("Writing link");
            stream.writeEmptyElement(TAG_LINK);
            String s;
            if ((s = src.getEdgeName()) != null && (s.length() > 0)) {
                stream.writeAttribute(EDGE_NAME, s);
            }
            if ((s = src.getId()) != null && (s.length() > 0)) {
                stream.writeAttribute(NODE_ID, s);
            }
            return;
        }

        boolean emptyElement = !((children = src.getInfoNodes()) != null && children.length > 0);

        String name = src.getName();
        if (emptyElement) {
            Log.getLogger().debug("Writing empty tag [" + name + "]");
            stream.writeEmptyElement(name);
        } else {
            Log.getLogger().debug("Writing start tag [" + name + "]");
            stream.writeStartElement(name);
        }

        Log.getLogger().debug("Writing attributes");
        if ((attrs = src.getAttrs()) != null && attrs.length > 0) {
            for (String ord : attrsOrder) {
                String val = src.getAttr(ord, null);
                if (val != null && val.length() > 0) {
                    stream.writeAttribute(ord, val);
                }
            }
            for (String str : attrs) {
                if (!attrsOrder.contains(str)) {
                    String val = src.getAttr(str, null);
                    if (val != null && val.length() > 0) {
                        stream.writeAttribute(str, val);
                    }
                }
            }
        }
        String s;
        if ((s = src.getEdgeName()) != null && (s.length() > 0)) {
            stream.writeAttribute(EDGE_NAME, s);
        }
        if ((s = src.getId()) != null && (s.length() > 0)) {
            stream.writeAttribute(NODE_ID, s);
        }

        if (!emptyElement) {
            for (InfoNode node : children) {
                write(node);
            }
        }

        if (!emptyElement) {
            Log.getLogger().debug("Ending tag");
            stream.writeEndElement();
        }
    }

    @Override
    public void setAttributesOrder(String[] names) {
        Log.getLogger().debug("Applying attributes order");
        attrsOrder.clear();
        for (String item : names) {
            if (!attrsOrder.contains(item)) {
                attrsOrder.add(new String(item));
            }
        }
    }
}
