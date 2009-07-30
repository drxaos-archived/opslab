package ru.org.opslab.common.formats.graphnode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

import ru.org.opslab.common.errors.IncorrectInput;
import ru.org.opslab.common.errors.NoSuchAttributeException;
import ru.org.opslab.common.errors.ParameterMustNotBeNull;

/**
 * Узел структуры
 */
public class GraphNode {

    /** Имя узла */
    private String _name;

    /** Список атрибутов узла */
    protected Map<String, String> _attr;

    /** Список дочерних связей узла */
    protected List<GraphEdge> _children;

    /** Список родительских связей узла */
    protected List<GraphEdge> _parents;

    /** Поле для раскраски графа */
    protected boolean mark;

    /** Поле для сериализации графа */
    protected String idMark;

    /** Флаг для узлов, которым нужны будут ссылки */
    protected boolean needMark;

    /** Счетчик для генерации _nodeId */
    private static int idCounter;

    /**
     * Конструктор. Создает узел с именем нулевой длины и пустыми списками атрибутов и дочерних ветвей.
     */
    public GraphNode() {
        _name = "";
        _attr = new TreeMap<String, String>();
        _attr.clear();
        _parents = new LinkedList<GraphEdge>();
        _parents.clear();
        _children = new LinkedList<GraphEdge>();
        _children.clear();
        mark = false;
        idMark = null;
    }

    /**
     * Конструктор. Запоминает имя создаваемого узла.
     * 
     * @param name
     *            Имя узла
     * @throws ParameterMustNotBeNull
     */
    public GraphNode(String name) throws ParameterMustNotBeNull {
        this();
        if (name == null) {
            throw new ParameterMustNotBeNull("Name must not be null");
        } else {
            _name = name;
        }
    }

    /**
     * Конструктор. Запоминает имя создаваемого узла и прикрепляет к указанному родителю.
     * 
     * @param name
     *            Имя узла
     * @throws ParameterMustNotBeNull
     *             Параметры не должны быть <b>null</b>
     */
    public GraphNode(String name, GraphNode parent) throws ParameterMustNotBeNull {
        this(name);
        GraphEdge.connect(parent, this);
    }

    /**
     * Создать новый узел и прикрепить к текущему.
     * 
     * @return Прикрепленный узел
     */
    public GraphNode addChild() {
        GraphNode node = new GraphNode();
        try {
            GraphEdge.connect(this, node);
        } catch (ParameterMustNotBeNull e) {
            // Unreachable code
            return null;
        }
        return node;
    }

    /**
     * Создать новый узел, прикрепить к текущему и запомнить название связи.
     * 
     * @param edgeName
     *            Название связи
     * @return Прикрепленный узел
     * @throws ParameterMustNotBeNull
     *             Передаваемый параметр не должен быть <b>null</b>
     */

    public GraphNode addChild(String edgeName) throws ParameterMustNotBeNull {
        GraphNode node = new GraphNode();
        GraphEdge.connect(this, node, edgeName);
        return node;
    }

    /**
     * Прикрепить указанный узел к текущему.
     * 
     * @return Прикрепленный узел
     * @throws ParameterMustNotBeNull
     *             Передаваемый параметр не должен быть <b>null</b>
     */
    public GraphNode addChild(GraphNode node) throws ParameterMustNotBeNull {
        GraphEdge.connect(this, node);
        return node;
    }

    /**
     * Прикрепить указанный узел к текущему и запомнить название связи.
     * 
     * @param edgeName
     *            Название связи
     * @return Прикрепленный узел
     * @throws ParameterMustNotBeNull
     *             Передаваемые параметры не должны быть <b>null</b>
     */
    public GraphNode addChild(GraphNode node, String edgeName) throws ParameterMustNotBeNull {
        GraphEdge.connect(this, node, edgeName);
        return node;
    }

    /**
     * Создать новый узел и прикрепить к нему текущий.
     * 
     * @return Новый узел
     */
    public GraphNode addParent() {
        GraphNode node = new GraphNode();
        try {
            GraphEdge.connect(node, this);
        } catch (ParameterMustNotBeNull e) {
            // Unreachable code
            return null;
        }
        return node;
    }

    /**
     * Создать новый узел, прикрепить к нему текущий и запомнить название связи.
     * 
     * @param edgeName
     *            Название связи
     * @return Новый узел
     * @throws ParameterMustNotBeNull
     *             Передаваемые параметры не должны быть <b>null</b>
     */
    public GraphNode addParent(String edgeName) throws ParameterMustNotBeNull {
        GraphNode node = new GraphNode();
        GraphEdge.connect(node, this, edgeName);
        return node;
    }

    /**
     * Прикрепить к переданному узлу текущий и запомнить название связи.
     * 
     * @param node
     *            Передаваемый узел
     * @param edgeName
     *            Название связи
     * @return Передаваемый узел
     * @throws ParameterMustNotBeNull
     *             Передаваемые параметры не должны быть <b>null</b>
     */
    public GraphNode addParent(GraphNode node, String edgeName) throws ParameterMustNotBeNull {
        GraphEdge.connect(node, this, edgeName);
        return node;
    }

    /**
     * Прикрепить к переданному узлу текущий.
     * 
     * @param node
     *            Передаваемый узел
     * @return Передаваемый узел
     * @throws ParameterMustNotBeNull
     *             Передаваемые параметры не должны быть <b>null</b>
     */
    public GraphNode addParent(GraphNode node) throws ParameterMustNotBeNull {
        GraphEdge.connect(node, this);
        return node;
    }

    /**
     * Удалить все дочерние связи этого узла
     */
    public void removeChildren() {
        removeEdges(null, null, false);
    }

    /**
     * Удалить дочерние связи с указанным именем.
     * 
     * @param name
     *            Имя искомых связей.
     */
    public void removeChildren(String name) {
        removeEdges(null, name, false);
    }

    /**
     * Удалить дочерние связи с указанным узлом.
     * 
     * @param node
     *            Искомый узел
     */
    public void removeChildren(GraphNode node) {
        removeEdges(node, null, false);
    }

    /**
     * Удалить дочерние связи с указанным узлом, имеющие указанное имя.
     * 
     * @param node
     *            Искомый узел
     * @param name
     *            Имя искомых связей.
     */
    public void removeChildren(GraphNode node, String name) {
        removeEdges(node, name, false);
    }

    /**
     * Удалить все родительские связи этого узла
     */
    public void removeParents() {
        removeEdges(null, null, true);
    }

    /**
     * Удалить родительские связи с указанным именем.
     * 
     * @param name
     *            Имя искомых связей.
     */
    public void removeParents(String name) {
        removeEdges(null, name, true);
    }

    /**
     * Удалить родительские связи с указанным узлом.
     * 
     * @param node
     *            Искомый узел
     */
    public void removeParents(GraphNode node) {
        removeEdges(node, null, true);
    }

    /**
     * Удалить родительские связи с указанным узлом, имеющие указанное имя.
     * 
     * @param node
     *            Искомый узел
     * @param name
     *            Имя искомых связей.
     */
    public void removeParents(GraphNode node, String name) {
        removeEdges(node, name, true);
    }

    /**
     * Удалить все связи этого узла
     */
    public void removeEdges(GraphNode node, String edgeName, boolean parents) {
        GraphEdge[] list = getEdges(node, edgeName, parents);
        for (GraphEdge edge : list) {
            edge.destroy();
        }
    }

    private boolean checkSubMap(Map<String, String> map, Map<String, String> sub) {
        for (String k : sub.keySet()) {
            if (!map.containsKey(k) || !map.get(k).equals(sub.get(k))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Вспомогательная рекурсивная функция для findNodes(). Помечает текущий узел, перебирает дочерние узлы и вызывает поиск в них.
     * 
     * @param name
     *            Имя искомых узлов. Если <b>null</b>, то поиск всех узлов.
     * @param list
     *            Список, в который сохраняются найденные узлы.
     * @param up
     *            Если <b>true</b>, поиск среди родительских узлов.
     * @param attrs
     *            Атрибуты, которые должны совпадать с атрибутами узла, для добавления его в список найденных. Если <b>null</b>, то не проверяется.
     */
    private void storeNodes(String name, List<GraphNode> list, boolean up, Map<String, String> attrs) {
        if (!mark) {
            mark = true;
            if (name == null || _name.equals(name)) {
                if (attrs == null || checkSubMap(_attr, attrs)) {
                    list.add(this);
                }
            }
            if (up) {
                for (GraphEdge edge : _parents) {
                    edge._parent.storeNodes(name, list, up, attrs);
                }
            } else {
                for (GraphEdge edge : _children) {
                    edge._child.storeNodes(name, list, up, attrs);
                }
            }
        }
    }

    /**
     * Вспомогательная рекурсивная функция для findNodes(). Убирает отметки с узлов, по которым происходил поиск.
     * 
     * @param up
     *            Если <b>true</b>, поиск среди родительских узлов.
     */
    private void cleanMarks(boolean up) {
        if (mark) {
            mark = false;
            for (GraphEdge edge : (up ? _parents : _children)) {
                (up ? edge._parent : edge._child).cleanMarks(up);
            }
        }
    }

    /**
     * Ищет в дереве, начинающемся с этого узла, все узлы и возвращает их массив.
     * 
     * @return Массив, состоящий из найденных узлов. Массив нулевой длины, если ни одного узла не найдено.
     */
    public GraphNode[] findNodes() {
        return findNodes(null, false);
    }

    /**
     * Ищет в дереве, начинающемся с этого узла, узлы с заданным именем и возвращает их массив.
     * 
     * @param name
     *            Имя искомых узлов. Если <b>null</b>, то поиск всех узлов.
     * @return Массив, состоящий из найденных узлов. Массив нулевой длины, если ни одного узла не найдено.
     */
    public GraphNode[] findNodes(String name) {
        return findNodes(name, false);
    }

    /**
     * Ищет в дереве, начинающемся с этого узла, узлы с заданным именем и возвращает их массив. Может искать как в сторону дочерних узлов, так и в сторону родительских.
     * 
     * @param name
     *            Имя искомых узлов. Если <b>null</b>, то поиск всех узлов.
     * @param up
     *            Искать среди родительских узлов.
     * @return Массив, состоящий из найденных узлов. Массив нулевой длины, если ни одного узла не найдено.
     */
    public GraphNode[] findNodes(String name, boolean up) {
        return findNodes(name, up, null, null);
    }

    /**
     * Ищет в дереве, начинающемся с этого узла, все узлы с заданным атрибутом и возвращает их массив.
     * 
     * @param attrKey
     *            Атрибут, по которому идет поиск.
     * @param attrValue
     *            Значение фтрибута, по которому идет поиск.
     * @return Массив, состоящий из найденных узлов. Массив нулевой длины, если ни одного узла не найдено.
     */
    public GraphNode[] findNodes(String attrKey, String attrValue) {
        return findNodes(null, false, attrKey, attrValue);
    }

    /**
     * Ищет в дереве, начинающемся с этого узла, узлы с заданным именем и атрибутом и возвращает их массив.
     * 
     * @param name
     *            Имя искомых узлов. Если <b>null</b>, то поиск всех узлов.
     * @param attrKey
     *            Атрибут, по которому идет поиск.
     * @param attrValue
     *            Значение фтрибута, по которому идет поиск.
     * @return Массив, состоящий из найденных узлов. Массив нулевой длины, если ни одного узла не найдено.
     */
    public GraphNode[] findNodes(String name, String attrKey, String attrValue) {
        return findNodes(name, false, attrKey, attrValue);
    }

    /**
     * Ищет в дереве, начинающемся с этого узла, узлы с заданным именем и атрибутом и возвращает их массив. Может искать как в сторону дочерних узлов, так и в сторону родительских.
     * 
     * @param name
     *            Имя искомых узлов. Если <b>null</b>, то поиск всех узлов.
     * @param up
     *            Искать среди родительских узлов.
     * @param attrKey
     *            Атрибут, по которому идет поиск.
     * @param attrValue
     *            Значение фтрибута, по которому идет поиск.
     * @return Массив, состоящий из найденных узлов. Массив нулевой длины, если ни одного узла не найдено.
     */
    public GraphNode[] findNodes(String name, boolean up, String attrKey, String attrValue) {
        Map<String, String> attrs = null;
        if (attrKey != null && attrValue != null) {
            attrs = new HashMap<String, String>();
            attrs.put(attrKey, attrValue);
        }
        return findNodes(name, up, attrs);
    }

    /**
     * Ищет в дереве, начинающемся с этого узла, все узлы с заданными атрибутами и возвращает их массив.
     * 
     * @param attrs
     *            Атрибуты, по которым идет поиск.
     * @return Массив, состоящий из найденных узлов. Массив нулевой длины, если ни одного узла не найдено.
     */
    public GraphNode[] findNodes(Map<String, String> attrs) {
        return findNodes(null, false, attrs);
    }

    /**
     * Ищет в дереве, начинающемся с этого узла, узлы с заданным именем и атрибутами и возвращает их массив.
     * 
     * @param name
     *            Имя искомых узлов. Если <b>null</b>, то поиск всех узлов.
     * @param attrs
     *            Атрибуты, по которым идет поиск.
     * @return Массив, состоящий из найденных узлов. Массив нулевой длины, если ни одного узла не найдено.
     */
    public GraphNode[] findNodes(String name, Map<String, String> attrs) {
        return findNodes(name, false, attrs);
    }

    /**
     * Ищет в дереве, начинающемся с этого узла, узлы с заданным именем и атрибутами и возвращает их массив. Может искать как в сторону дочерних узлов, так и в сторону родительских.
     * 
     * @param name
     *            Имя искомых узлов. Если <b>null</b>, то поиск всех узлов.
     * @param up
     *            Искать среди родительских узлов.
     * @param attrs
     *            Атрибуты, по которым идет поиск.
     * @return Массив, состоящий из найденных узлов. Массив нулевой длины, если ни одного узла не найдено.
     */
    public GraphNode[] findNodes(String name, boolean up, Map<String, String> attrs) {
        List<GraphNode> list = new ArrayList<GraphNode>();
        storeNodes(name, list, up, attrs);
        cleanMarks(up);
        return list.toArray(new GraphNode[0]);
    }

    /**
     * Устанавливает атрибут узла
     * 
     * @param param
     *            Имя атрибута. Не может начинаться с "_"
     * @param value
     *            Новое значение. Если <b>null</b>, то существующий атрибут удаляется
     * @throws IncorrectInput
     *             Выбрасывается при передаче некорректных параметров
     */
    public void setAttr(String param, String value) throws IncorrectInput {
        if (param != null) {
            if (param.charAt(0) == '_') {
                throw new IncorrectInput("GraphNode attribute name cannot start with \"_\": " + param + "=" + value);
            }
            if (value != null) {
                _attr.put(param, value);
            } else {
                if (_attr.containsKey(param)) {
                    _attr.remove(param);
                }
            }
        } else {
            throw new IncorrectInput("GraphNode attribute name cannot be null");
        }
    }

    /**
     * Удаляет атрибут узла
     * 
     * @param param
     *            Имя атрибута. Не может начинаться с "_"
     * @throws IncorrectInput
     *             Выбрасывается при передаче некорректных параметров
     */
    public void removeAttr(String param) throws IncorrectInput {
        setAttr(param, null);
    }

    /**
     * Добавляет к текущему узлу все атрибуты из указанного узла.
     * 
     * @param source
     *            Узел-источник.
     */
    public void copyAttr(GraphNode source) {
        for (String k : source._attr.keySet()) {
            _attr.put(k, source._attr.get(k));
        }
    }

    /**
     * Добавляет к текущему узлу атрибут с указанным именем из указанного источника, если такой атрибут там есть.
     * 
     * @param source
     *            Узел-источник.
     * @param name
     *            Имя атрибута.
     */
    public void copyAttr(GraphNode source, String name) {
        if (source._attr.containsKey(name)) {
            _attr.put(name, source._attr.get(name));
        }
    }

    /**
     * Получает значение указанного атрибута.
     * 
     * @param param
     *            Имя атрибута.
     * @return Значение атрибута.
     * @throws NoSuchAttributeException
     *             Выбрасывается, если указанного атрибута нет.
     */
    public String getAttr(String param) throws NoSuchAttributeException {
        if (_attr.containsKey(param)) {
            return _attr.get(param);
        } else {
            throw new NoSuchAttributeException("Attribute [" + param + "] not found\n Node: " + toString());
        }
    }

    /**
     * Получает значение указанного атрибута. Если такого атрибута нет, возвращает defaultValue
     * 
     * @param param
     *            Имя атрибута.
     * @param defaultValue
     *            Значение по умолчанию.
     * @return Значение. Если указанного атрибута нет, возвращает defaultValue.
     */
    public String getAttr(String param, String defaultValue) {
        if (_attr.containsKey(param)) {
            return _attr.get(param);
        } else {
            return defaultValue;
        }
    }

    /**
     * Проверяет наличие атрибута.
     * 
     * @param param
     *            Имя атрибута.
     * @return true - если атрибут назначен, иначе false.
     */
    public boolean hasAttr(String param) {
        return _attr.containsKey(param);
    }

    /**
     * Проверяет значение атрибута на равенство заданному
     * 
     * @param param
     *            Имя атрибута.
     * @param value
     *            Заданное значение.
     * @return true если значения равны, иначе false
     */
    public boolean hasAttr(String param, String value) {
        if (_attr.containsKey(param)) {
            return _attr.get(param).equals(value);
        } else {
            return false;
        }
    }

    /**
     * Устанавливает имя узла.
     * 
     * @param name
     *            Новое имя.
     */
    public void setName(String name) {
        if (name == null) {
            _name = "";
        } else {
            _name = name;
        }
    }

    /**
     * Получает имя узла.
     * 
     * @return Имя узла.
     */
    public String getName() {
        if (_name != null) {
            return _name;
        } else {
            return "";
        }
    }

    /**
     * Возвращает массив дочерних узлов.
     * 
     * @return Массив дочерних узлов
     */
    public GraphNode[] getChildren() {
        return getNodes(null, false);
    }

    /**
     * Возвращает массив дочерних узлов с указанным именем.
     * 
     * @param name
     *            Имя искомых узлов. Если <b>null</b>, то параметр не проверяется.
     * @return Массив дочерних узлов. Массив нулевой длины, если нет дочерних узлов.
     */
    public GraphNode[] getChildren(String name) {
        return getNodes(name, false);
    }

    /**
     * Возвращает массив родительских узлов.
     * 
     * @return Массив дочерних узлов
     */
    public GraphNode[] getParents() {
        return getNodes(null, true);
    }

    /**
     * Возвращает массив родительских узлов с указанным именем.
     * 
     * @param name
     *            Имя искомых узлов. Если <b>null</b>, то параметр не проверяется.
     * @return Массив дочерних узлов. Массив нулевой длины, если нет дочерних узлов.
     */
    public GraphNode[] getParents(String name) {
        return getNodes(name, true);
    }

    /**
     * Возвращает массив связанных узлов, отвечающих заданным параметрам.
     * 
     * @param name
     *            Имя искомых узлов. Если <b>null</b>, то параметр не проверяется.
     * @param parents
     *            Если <b>true</b>, то искать родительские узлы. Иначе - дочерние.
     * @return Массив найденных узлов.
     */
    public GraphNode[] getNodes(String name, boolean parents) {
        if (name == null) {
            // без создания нового списка
            List<GraphEdge> list = (parents ? _parents : _children);
            GraphNode[] result = new GraphNode[list.size()];
            ListIterator<GraphEdge> it = list.listIterator();
            for (int i = 0; i < result.length; i++) {
                result[i] = (parents ? it.next()._parent : it.next()._child);
            }
            return result;
        } else {
            // создание нового списка
            List<GraphNode> list = new ArrayList<GraphNode>();
            for (GraphEdge edge : (parents ? _parents : _children)) {
                GraphNode node = (parents ? edge._parent : edge._child);
                if (name == null || node._name.equals(name)) {
                    list.add(node);
                }
            }
            return list.toArray(new GraphNode[0]);
        }
    }

    /**
     * Возвращает массив дочерних связей.
     * 
     * @return Массив дочерних связей
     */
    public GraphEdge[] getChildEdges() {
        return getEdges(null, null, false);
    }

    /**
     * Возвращает массив дочерних связей с указанным именем.
     * 
     * @param name
     *            Имя искомых связей. Если <b>null</b>, то параметр не проверяется.
     * @return Массив дочерних связей. Массив нулевой длины, если нет дочерних связей.
     */
    public GraphEdge[] getChildEdges(String name) {
        return getEdges(null, name, false);
    }

    /**
     * Возвращает массив дочерних связей с указанным узлом.
     * 
     * @param node
     *            Искомый узел. Если <b>null</b>, то параметр не проверяется.
     * @return Массив дочерних связей. Массив нулевой длины, если нет дочерних связей.
     */
    public GraphEdge[] getChildEdges(GraphNode node) {
        return getEdges(node, null, false);
    }

    /**
     * Возвращает массив дочерних связей указанного имени с указанным узлом.
     * 
     * @param node
     *            Искомый узел. Если <b>null</b>, то параметр не проверяется.
     * @param name
     *            Имя искомых связей. Если <b>null</b>, то параметр не проверяется.
     * @return Массив дочерних связей. Массив нулевой длины, если нет дочерних связей.
     */
    public GraphEdge[] getChildEdges(GraphNode node, String name) {
        return getEdges(node, name, false);
    }

    /**
     * Возвращает массив родительских связей.
     * 
     * @return Массив родительских связей
     */
    public GraphEdge[] getParentEdges() {
        return getEdges(null, null, true);
    }

    /**
     * Возвращает массив родительских связей с указанным именем.
     * 
     * @param name
     *            Имя искомых связей. Если <b>null</b>, то параметр не проверяется.
     * @return Массив родительских связей. Массив нулевой длины, если нет родительских связей.
     */
    public GraphEdge[] getParentEdges(String name) {
        return getEdges(null, name, true);
    }

    /**
     * Возвращает массив родительских связей с указанным узлом.
     * 
     * @param node
     *            Искомый узел. Если <b>null</b>, то параметр не проверяется.
     * @return Массив родительских связей. Массив нулевой длины, если нет родительских связей.
     */
    public GraphEdge[] getParentEdges(GraphNode node) {
        return getEdges(node, null, true);
    }

    /**
     * Возвращает массив родительских связей указанного имени с указанным узлом.
     * 
     * @param node
     *            Искомый узел. Если <b>null</b>, то параметр не проверяется.
     * @param name
     *            Имя искомых связей. Если <b>null</b>, то параметр не проверяется.
     * @return Массив родительских связей. Массив нулевой длины, если нет родительских связей.
     */
    public GraphEdge[] getParentEdges(GraphNode node, String name) {
        return getEdges(node, name, true);
    }

    /**
     * Возвращает массив связей, отвечающих заданным параметрам.
     * 
     * @param node
     *            Искомый узел. Если <b>null</b>, то параметр не проверяется.
     * @param name
     *            Имя искомых связей. Если <b>null</b>, то параметр не проверяется.
     * @param parents
     *            Если <b>true</b>, то искать родительские связи. Иначе - дочерние.
     * @return Массив найденных связей.
     */
    public GraphEdge[] getEdges(GraphNode node, String name, boolean parents) {
        if (node == null && name == null) {
            return (parents ? _parents : _children).toArray(new GraphEdge[0]);
        } else {
            List<GraphEdge> list = new ArrayList<GraphEdge>();
            for (GraphEdge edge : (parents ? _parents : _children)) {
                if ((name == null || edge._name.equals(name)) && (node == null || (parents ? edge._parent : edge._child) == node)) {
                    list.add(edge);
                }
            }
            return list.toArray(new GraphEdge[0]);
        }
    }

    /**
     * Вспомогательная рекурсивная функция для updateInfo(). Убирает отметки и информационные узлы.
     */
    private void cleanIdMarks(boolean delete) {
        if (!mark) {
            needMark = false;
            mark = true;
            idMark = null;
            for (GraphEdge edge : _children) {
                if (!delete) {
                    if (edge._info == null) {
                        edge._info = new InfoNode(null, null, null);
                    } else {
                        edge._info.set(null, null, null);
                    }
                } else {
                    edge._info = null;
                }
                edge._child.cleanIdMarks(delete);
            }
        } else {
            needMark = true;
        }
    }

    /**
     * Вспомогательная рекурсивная функция для updateInfo(). Помечает текущий узел, создает для него информационный узел, перебирает дочерние узлы и вызывает поиск в них.
     */
    private void storeInfo(GraphEdge edge) {
        if (edge != null) {
            String id;
            GraphNode node;

            if (edge._child.idMark == null) {
                if (edge._child.needMark) {
                    // generate id
                    idCounter++;
                    id = edge._child._name + "_" + edge._child.getAttr("name", "null") + "_id" + idCounter;
                    edge._child.idMark = id;
                } else {
                    id = null;
                }
                node = edge._child; // <Tag>
            } else {
                id = edge._child.idMark;
                node = null; // <Edge>
            }

            if (edge._info == null) {
                edge._info = new InfoNode(node, id, edge);
            } else {
                edge._info.set(node, id, edge);
            }
        }
        if (mark) {
            mark = false;
            for (GraphEdge e : _children) {
                e._child.storeInfo(e);
            }
        }

    }

    /**
     * Создать информационные узлы для сериализации графа.
     */
    public void updateInfo() {
        idCounter = 0;
        cleanIdMarks(false);
        storeInfo(null);
    }

    /**
     * Удалить информационные узлы. Может освободить немного памяти.
     */
    public void removeInfo() {
        cleanIdMarks(true);
        cleanMarks(false);
    }

    /**
     * Получить информационные узлы, указывающие на дочерние узлы данного узла.
     * 
     * @return Массив информационных узлов
     */
    public InfoNode[] getInfoNodes() {
        List<GraphEdge> list = _children;
        InfoNode[] result = new InfoNode[list.size()];
        ListIterator<GraphEdge> it = list.listIterator();
        for (int i = 0; i < result.length; i++) {
            result[i] = it.next()._info;
        }
        return result;
    }

    /**
     * Получить информационный узел для текущего узла как для корневого.
     * 
     * @return Информационный узел
     */
    public InfoNode getRootInfoNode() {
        return new InfoNode(this, null, null);
    }

    /**
     * Возвращает массив имен атрибутов (без их значений).
     * 
     * @return Массив имен атрибутов. Массив нулевой длины, если атрибутов нет.
     */
    public String[] getAttrs() {
        return _attr.keySet().toArray(new String[0]);
    }

    /**
     * Проверить, является ли узел комментарием.
     * 
     * @return true если узел является комментарием, иначе - false.
     */
    public boolean isComment() {
        return false;
    }

    /**
     * Проверить, является ли узел текстом.
     * 
     * @return true если узел является текстом, иначе - false.
     */
    public boolean isText() {
        return false;
    }

    /**
     * Проверить, является ли узел тегом.
     * 
     * @return true если узел является тегом, иначе - false.
     */
    public boolean isTag() {
        return true;
    }

    private String makeString(int indention) {
        int i = indention;
        String pre = "";
        while (i-- > 0) {
            pre += " ";
        }
        if (indention > 10) {
            return pre + "...\n";
        }
        StringBuilder buf = new StringBuilder(pre + _name + " ");
        for (String k : _attr.keySet()) {
            buf.append(k + "=" + _attr.get(k) + " ");
        }
        buf.append("\n");
        for (GraphEdge n : _children) {
            buf.append(n._child.makeString(indention + 1));
        }
        return buf.toString();
    }

    @Override
    public String toString() {
        return makeString(0);
    }
}
