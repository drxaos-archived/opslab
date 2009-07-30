package ru.org.opslab.common.formats.graphnode;

import ru.org.opslab.common.errors.NoSuchAttributeException;

public class InfoNode {

    GraphNode _node;
    String _id;
    GraphEdge _edge;

    InfoNode() {
        // Empty constructor
    }

    InfoNode(GraphNode node, String id, GraphEdge edge) {
        set(node, id, edge);
    }

    void set(GraphNode node, String id, GraphEdge edge) {
        _node = node;
        _id = id;
        _edge = edge;
    }

    public GraphNode getNode() {
        return _node;
    }

    public String getId() {
        return _id;
    }

    public String getEdgeName() {
        if (_edge != null) {
            return _edge.getName();
        } else {
            return null;
        }
    }

    public InfoNode[] getInfoNodes() {
        if (_node != null) {
            return _node.getInfoNodes();
        } else {
            return null;
        }
    }

    public String getAttr(String param) throws NoSuchAttributeException {
        if (_node != null) {
            return _node.getAttr(param);
        } else {
            return null;
        }
    }

    public String getAttr(String param, String defaultValue) {
        if (_node != null) {
            return _node.getAttr(param, defaultValue);
        } else {
            return null;
        }
    }

    public String[] getAttrs() {
        if (_node != null) {
            return _node.getAttrs();
        } else {
            return null;
        }
    }

    public String getName() {
        if (_node != null) {
            return _node.getName();
        } else {
            return null;
        }
    }

    public boolean isComment() {
        return _node != null && _node.isComment();
    }

    public boolean isText() {
        return _node != null && _node.isText();
    }

    public boolean isTag() {
        return _node != null && _node.isTag();
    }

    public boolean isLink() {
        return _node == null;
    }

}
