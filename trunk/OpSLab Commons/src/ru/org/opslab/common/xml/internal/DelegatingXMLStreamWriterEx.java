package ru.org.opslab.common.xml.internal;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

abstract class DelegatingXMLStreamWriterEx implements XMLStreamWriterEx {

    public DelegatingXMLStreamWriterEx(XMLStreamWriter xmlstreamwriter) {
        writer = xmlstreamwriter;
    }

    public void writeStartElement(String s) throws XMLStreamException {
        writer.writeStartElement(s);
    }

    public void writeStartElement(String s, String s1) throws XMLStreamException {
        writer.writeStartElement(s, s1);
    }

    public void writeStartElement(String s, String s1, String s2) throws XMLStreamException {
        writer.writeStartElement(s, s1, s2);
    }

    public void writeEmptyElement(String s, String s1) throws XMLStreamException {
        writer.writeEmptyElement(s, s1);
    }

    public void writeEmptyElement(String s, String s1, String s2) throws XMLStreamException {
        writer.writeEmptyElement(s, s1, s2);
    }

    public void writeEmptyElement(String s) throws XMLStreamException {
        writer.writeEmptyElement(s);
    }

    public void writeEndElement() throws XMLStreamException {
        writer.writeEndElement();
    }

    public void writeEndDocument() throws XMLStreamException {
        writer.writeEndDocument();
    }

    public void close() throws XMLStreamException {
        writer.close();
    }

    public void flush() throws XMLStreamException {
        writer.flush();
    }

    public void writeAttribute(String s, String s1) throws XMLStreamException {
        writer.writeAttribute(s, s1);
    }

    public void writeAttribute(String s, String s1, String s2, String s3) throws XMLStreamException {
        writer.writeAttribute(s, s1, s2, s3);
    }

    public void writeAttribute(String s, String s1, String s2) throws XMLStreamException {
        writer.writeAttribute(s, s1, s2);
    }

    public void writeNamespace(String s, String s1) throws XMLStreamException {
        writer.writeNamespace(s, s1);
    }

    public void writeDefaultNamespace(String s) throws XMLStreamException {
        writer.writeDefaultNamespace(s);
    }

    public void writeComment(String s) throws XMLStreamException {
        writer.writeComment(s);
    }

    public void writeText(String s) throws XMLStreamException {
        writer.writeCharacters(s);
    }

    public void writeProcessingInstruction(String s) throws XMLStreamException {
        writer.writeProcessingInstruction(s);
    }

    public void writeProcessingInstruction(String s, String s1) throws XMLStreamException {
        writer.writeProcessingInstruction(s, s1);
    }

    public void writeCData(String s) throws XMLStreamException {
        writer.writeCData(s);
    }

    public void writeDTD(String s) throws XMLStreamException {
        writer.writeDTD(s);
    }

    public void writeEntityRef(String s) throws XMLStreamException {
        writer.writeEntityRef(s);
    }

    public void writeStartDocument() throws XMLStreamException {
        writer.writeStartDocument();
    }

    public void writeStartDocument(String s) throws XMLStreamException {
        writer.writeStartDocument(s);
    }

    public void writeStartDocument(String s, String s1) throws XMLStreamException {
        writer.writeStartDocument(s, s1);
    }

    public void writeCharacters(String s) throws XMLStreamException {
        writer.writeCharacters(s);
    }

    public void writeCharacters(char ac[], int i, int j) throws XMLStreamException {
        writer.writeCharacters(ac, i, j);
    }

    public String getPrefix(String s) throws XMLStreamException {
        return writer.getPrefix(s);
    }

    public void setPrefix(String s, String s1) throws XMLStreamException {
        writer.setPrefix(s, s1);
    }

    public void setDefaultNamespace(String s) throws XMLStreamException {
        writer.setDefaultNamespace(s);
    }

    public void setNamespaceContext(NamespaceContext namespacecontext) throws XMLStreamException {
        writer.setNamespaceContext(namespacecontext);
    }

    public NamespaceContext getNamespaceContext() {
        return writer.getNamespaceContext();
    }

    public Object getProperty(String s) throws IllegalArgumentException {
        return writer.getProperty(s);
    }

    private final XMLStreamWriter writer;
}
