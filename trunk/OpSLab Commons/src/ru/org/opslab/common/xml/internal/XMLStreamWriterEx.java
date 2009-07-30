package ru.org.opslab.common.xml.internal;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public interface XMLStreamWriterEx extends XMLStreamWriter {
    /**
     * Writes some text with indention like an element
     * 
     * @param data
     *            the data contained in the comment, may be null
     * @throws XMLStreamException
     */
    public void writeText(String data) throws XMLStreamException;

}
