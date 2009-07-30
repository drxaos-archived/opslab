package ru.org.opslab.common.formats.graphnode;

import ru.org.opslab.common.errors.ParameterMustNotBeNull;

/**
 * Направленная связь между двумя узлами
 */
public class GraphEdge {

    protected GraphNode _parent;
    protected GraphNode _child;
    protected String _name;
    protected InfoNode _info;

    public GraphEdge(GraphNode parent, GraphNode child, String name) throws ParameterMustNotBeNull {
        if (name == null || parent == null || child == null) {
            throw new ParameterMustNotBeNull("GraphEdge creation with incorrect parameters");
        }
        _name = name;
        _parent = parent;
        _child = child;

        // Connecting nodes
        _parent._children.add(this);
        _child._parents.add(this);
    }

    public GraphEdge(GraphNode parent, GraphNode child) throws ParameterMustNotBeNull {
        this(parent, child, "");
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) throws ParameterMustNotBeNull {
        if (name == null) {
            throw new ParameterMustNotBeNull("Name cannot be null");
        }
        // if edge is valid
        if (_name != null) {
            _name = name;
        }
    }

    public GraphNode getChild() {
        return _child;
    }

    public GraphNode getParent() {
        return _parent;
    }

    public void destroy() {
        // Disconnecting nodes
        _parent._children.remove(this);
        _child._parents.remove(this);

        _parent = null;
        _child = null;
        _name = null;
    }

    public boolean isValid() {
        return (_name != null);
    }

    public static GraphEdge connect(GraphNode parent, GraphNode child, String name) throws ParameterMustNotBeNull {
        return new GraphEdge(parent, child, name);
    }

    public static GraphEdge connect(GraphNode parent, GraphNode child) throws ParameterMustNotBeNull {
        return GraphEdge.connect(parent, child, "");
    }

    public static GraphEdge[] getConnections(GraphNode parent, GraphNode child) throws ParameterMustNotBeNull {
        return parent.getChildEdges(child);
    }

}
