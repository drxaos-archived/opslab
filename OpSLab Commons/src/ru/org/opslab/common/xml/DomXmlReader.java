package ru.org.opslab.common.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ru.org.opslab.common.errors.IncorrectInput;
import ru.org.opslab.common.errors.ParameterMustNotBeNull;
import ru.org.opslab.common.formats.graphnode.GraphEdge;
import ru.org.opslab.common.formats.graphnode.GraphNode;
import ru.org.opslab.common.formats.graphnode.GraphNodeText;
import ru.org.opslab.common.utils.logging.Log;

/**
 * Парсер из Xml документа в дерево узлов.
 */
public class DomXmlReader implements XmlReader, XmlStrings {

    private Map<String, GraphNode> table;

    /**
     * Чтение атрибутов.
     * 
     * @param element
     *            Узел DOM.
     * @param treeNode
     *            Целевой узел.
     * @throws IncorrectInput
     * @throws ParameterMustNotBeNull
     */
    private void parseXMLAttr(Node element, GraphNode treeNode, GraphEdge edge) throws IncorrectInput, ParameterMustNotBeNull {
        if (element.hasAttributes()) {
            NamedNodeMap attributes = element.getAttributes();
            for (int j = 0; j < attributes.getLength(); j++) {
                Attr attribute = (Attr) (attributes.item(j));
                if (attribute.getName().trim().equals(NODE_ID)) {
                    String k = attribute.getValue().trim();
                    if (treeNode.getName().equals(TAG_LINK)) {
                        if (table.containsKey(k)) {
                            Log.getLogger().debug("Linking edge from table");
                            GraphNode n = table.get(k);
                            new GraphEdge(edge.getParent(), n, edge.getName());
                            edge.destroy();
                        } else {
                            Log.getLogger().error("Edge id not found in table");
                            throw new IncorrectInput("Xml has incorrect edges");
                        }
                    } else {
                        if (!table.containsKey(k)) {
                            table.put(k, treeNode);
                        }
                    }
                } else if (attribute.getName().trim().equals(EDGE_NAME)) {
                    edge.setName(attribute.getValue().trim());
                } else {
                    treeNode.setAttr(attribute.getName().trim(), attribute.getValue().trim());
                }
            }
        }
    }

    /**
     * Чтение узла.
     * 
     * @param element
     *            Узел DOM
     * @param treeNode
     *            Целевой узел.
     * @throws ParameterMustNotBeNull
     * @throws IncorrectInput
     */
    private void parseXMLNode(Node element, GraphNode treeNode, GraphEdge edge) throws ParameterMustNotBeNull, IncorrectInput {
        //name
        String elname = element.getNodeName();
        Log.getLogger().debug("Parsing name [" + elname + "]");
        treeNode.setName(elname);

        //attributes
        Log.getLogger().debug("Parsing attributes");
        parseXMLAttr(element, treeNode, edge);

        //children
        Log.getLogger().debug("Parsing children");
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) { //тег
                Node childElement = node;
                String name = childElement.getNodeName();
                Log.getLogger().debug("Parsing tag [" + name + "]");
                GraphNode childNode = new GraphNode(name);
                GraphEdge e = new GraphEdge(treeNode, childNode);
                parseXMLNode(childElement, childNode, e);
            } else if (node.getNodeType() == Node.TEXT_NODE) { //текст
                Log.getLogger().debug("Parsing text");
                String str = node.getNodeValue().trim();
                if (str.length() > 0) {
                    new GraphNodeText(str.trim(), false, treeNode);
                }
            } else if (node.getNodeType() == Node.COMMENT_NODE) { //комментарий
                Log.getLogger().debug("Parsing comment");
                String str = node.getNodeValue().trim();
                if (str.length() > 0) {
                    new GraphNodeText(str.trim(), true, treeNode);
                }
            }
        }
    }

    public GraphNode readXml(InputStream in) throws ParserConfigurationException, SAXException, IOException, ParameterMustNotBeNull, IncorrectInput {
        Log.getLogger().info("Reading XML stream");

        //чтение потока в DOM представление
        Log.getLogger().debug("Reading XML into DOM");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(in);

        table = new TreeMap<String, GraphNode>();

        //корневые узлы DOM и TreeNode
        Node rootElement = document.getDocumentElement();
        GraphNode root = new GraphNode();
        //конвертация из DOM в TreeNode
        Log.getLogger().debug("Parsing DOM");
        parseXMLNode(rootElement, root, null);

        return root;
    }
}
