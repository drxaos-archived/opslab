package ru.org.opslab.common.formats.graphnode;

import ru.org.opslab.common.errors.ParameterMustNotBeNull;

/**
 * Текст
 */
public class GraphNodeText extends GraphNode {

    private String _text;
    private boolean _comment;

    public GraphNodeText() {
        try {
            setTextParams("", false);
        } catch (ParameterMustNotBeNull e) {
            // unreachable line
        }
    }

    public GraphNodeText(String text) throws ParameterMustNotBeNull {
        setTextParams(text, false);
    }

    public GraphNodeText(boolean isComment) {
        try {
            setTextParams("", isComment);
        } catch (ParameterMustNotBeNull e) {
            // unreachable line
        }
    }

    public GraphNodeText(String text, boolean isComment) throws ParameterMustNotBeNull {
        setTextParams(text, isComment);
    }

    public GraphNodeText(String text, boolean isComment, GraphNode parent) throws ParameterMustNotBeNull {
        super("", parent);
        setTextParams(text, isComment);
    }

    public void setTextParams(String text, boolean isComment) throws ParameterMustNotBeNull {
        if (text == null) {
            throw new ParameterMustNotBeNull("Node text cannot be null");
        }
        _text = text;
        _comment = isComment;
    }

    /**
     * Получить содержимое текстового узла
     * 
     * @return Текст
     */
    public String getText() {
        return _text;
    }

    /**
     * Установить содержимое текстового узла
     * 
     * @param text
     *            Текст
     */
    public void setText(String text) {
        _text = text;
    }

    public void setType(boolean isComment) {
        _comment = isComment;
    }

    /**
     * Проверить, является ли узел комментарием.
     * 
     * @return true если узел является комментарием, иначе - false.
     */
    @Override
    public boolean isComment() {
        return _comment;
    }

    /**
     * Проверить, является ли узел текстом.
     * 
     * @return true если узел является текстом, иначе - false.
     */
    @Override
    public boolean isText() {
        return !_comment;
    }

    /**
     * Проверить, является ли узел тегом.
     * 
     * @return true если узел является тегом, иначе - false.
     */
    @Override
    public boolean isTag() {
        return false;
    }

}
