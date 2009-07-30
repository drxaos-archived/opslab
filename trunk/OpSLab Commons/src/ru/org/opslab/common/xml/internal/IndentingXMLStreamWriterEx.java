package ru.org.opslab.common.xml.internal;

import java.util.Stack;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class IndentingXMLStreamWriterEx extends DelegatingXMLStreamWriterEx {

    @SuppressWarnings("unchecked")
    public IndentingXMLStreamWriterEx(XMLStreamWriter xmlstreamwriter) {
        super(xmlstreamwriter);
        state = IndentingXMLStreamWriterEx.SEEN_NOTHING;
        stateStack = new Stack();
        indentStep = "  ";
        depth = 0;
    }

    /**
     * @deprecated Method getIndentStep is deprecated
     */

    @Deprecated
    public int getIndentStep() {
        return indentStep.length();
    }

    /**
     * @deprecated Method setIndentStep is deprecated
     */

    @Deprecated
    public void setIndentStep(int i) {
        StringBuilder stringbuilder = new StringBuilder();
        for (; i > 0; i--) {
            stringbuilder.append(' ');
        }

        setIndentStep(stringbuilder.toString());
    }

    public void setIndentStep(String s) {
        indentStep = s;
    }

    @SuppressWarnings("unchecked")
    private void onStartElement() throws XMLStreamException {
        stateStack.push(IndentingXMLStreamWriterEx.SEEN_ELEMENT);
        state = IndentingXMLStreamWriterEx.SEEN_NOTHING;
        if (depth > 0) {
            super.writeCharacters("\n");
        }
        doIndent();
        depth++;
    }

    private void onEndElement() throws XMLStreamException {
        depth--;
        if (state == IndentingXMLStreamWriterEx.SEEN_ELEMENT) {
            super.writeCharacters("\n");
            doIndent();
        }
        state = stateStack.pop();
    }

    private void onEmptyElement() throws XMLStreamException {
        state = IndentingXMLStreamWriterEx.SEEN_ELEMENT;
        if (depth > 0) {
            super.writeCharacters("\n");
        }
        doIndent();
    }

    private void doIndent() throws XMLStreamException {
        if (depth > 0) {
            for (int i = 0; i < depth; i++) {
                super.writeCharacters(indentStep);
            }

        }
    }

    @Override
    public void writeStartDocument() throws XMLStreamException {
        super.writeStartDocument();
        super.writeCharacters("\n");
    }

    @Override
    public void writeStartDocument(String s) throws XMLStreamException {
        super.writeStartDocument(s);
        super.writeCharacters("\n");
    }

    @Override
    public void writeStartDocument(String s, String s1) throws XMLStreamException {
        super.writeStartDocument(s, s1);
        super.writeCharacters("\n");
    }

    @Override
    public void writeStartElement(String s) throws XMLStreamException {
        onStartElement();
        super.writeStartElement(s);
    }

    @Override
    public void writeStartElement(String s, String s1) throws XMLStreamException {
        onStartElement();
        super.writeStartElement(s, s1);
    }

    @Override
    public void writeStartElement(String s, String s1, String s2) throws XMLStreamException {
        onStartElement();
        super.writeStartElement(s, s1, s2);
    }

    @Override
    public void writeEmptyElement(String s, String s1) throws XMLStreamException {
        onEmptyElement();
        super.writeEmptyElement(s, s1);
    }

    @Override
    public void writeEmptyElement(String s, String s1, String s2) throws XMLStreamException {
        onEmptyElement();
        super.writeEmptyElement(s, s1, s2);
    }

    @Override
    public void writeEmptyElement(String s) throws XMLStreamException {
        onEmptyElement();
        super.writeEmptyElement(s);
    }

    @Override
    public void writeEndElement() throws XMLStreamException {
        onEndElement();
        super.writeEndElement();
    }

    @Override
    public void writeCharacters(String s) throws XMLStreamException {
        state = IndentingXMLStreamWriterEx.SEEN_DATA;
        super.writeCharacters(s);
    }

    @Override
    public void writeCharacters(char ac[], int i, int j) throws XMLStreamException {
        state = IndentingXMLStreamWriterEx.SEEN_DATA;
        super.writeCharacters(ac, i, j);
    }

    @Override
    public void writeCData(String s) throws XMLStreamException {
        state = IndentingXMLStreamWriterEx.SEEN_DATA;
        super.writeCData(s);
    }

    @Override
    public Object getProperty(String s) throws IllegalArgumentException {
        return super.getProperty(s);
    }

    @Override
    public NamespaceContext getNamespaceContext() {
        return super.getNamespaceContext();
    }

    @Override
    public void setNamespaceContext(NamespaceContext namespacecontext) throws XMLStreamException {
        super.setNamespaceContext(namespacecontext);
    }

    @Override
    public void setDefaultNamespace(String s) throws XMLStreamException {
        super.setDefaultNamespace(s);
    }

    @Override
    public void setPrefix(String s, String s1) throws XMLStreamException {
        super.setPrefix(s, s1);
    }

    @Override
    public String getPrefix(String s) throws XMLStreamException {
        return super.getPrefix(s);
    }

    @Override
    public void writeEntityRef(String s) throws XMLStreamException {
        super.writeEntityRef(s);
    }

    @Override
    public void writeDTD(String s) throws XMLStreamException {
        super.writeDTD(s);
    }

    @Override
    public void writeProcessingInstruction(String s, String s1) throws XMLStreamException {
        super.writeProcessingInstruction(s, s1);
    }

    @Override
    public void writeProcessingInstruction(String s) throws XMLStreamException {
        super.writeProcessingInstruction(s);
    }

    @Override
    public void writeComment(String s) throws XMLStreamException {
        onEmptyElement();
        super.writeComment(s);
        if (depth == 0) {
            super.writeCharacters("\n");
        }
    }

    @Override
    public void writeText(String s) throws XMLStreamException {
        onEmptyElement();
        super.writeText(s);
        if (depth == 0) {
            super.writeCharacters("\n");
        }
    }

    @Override
    public void writeDefaultNamespace(String s) throws XMLStreamException {
        super.writeDefaultNamespace(s);
    }

    @Override
    public void writeNamespace(String s, String s1) throws XMLStreamException {
        super.writeNamespace(s, s1);
    }

    @Override
    public void writeAttribute(String s, String s1, String s2) throws XMLStreamException {
        super.writeAttribute(s, s1, s2);
    }

    @Override
    public void writeAttribute(String s, String s1, String s2, String s3) throws XMLStreamException {
        super.writeAttribute(s, s1, s2, s3);
    }

    @Override
    public void writeAttribute(String s, String s1) throws XMLStreamException {
        super.writeAttribute(s, s1);
    }

    @Override
    public void flush() throws XMLStreamException {
        super.flush();
    }

    @Override
    public void close() throws XMLStreamException {
        super.close();
    }

    @Override
    public void writeEndDocument() throws XMLStreamException {
        super.writeEndDocument();
    }

    private static final Object SEEN_NOTHING = new Object();
    private static final Object SEEN_ELEMENT = new Object();
    private static final Object SEEN_DATA = new Object();
    private Object state;
    @SuppressWarnings("unchecked")
    private Stack stateStack;
    private String indentStep;
    private int depth;

}
