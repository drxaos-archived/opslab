package ru.org.opslab.common.junit;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;

import org.junit.Test;

import ru.org.opslab.common.formats.graphnode.GraphNode;
import ru.org.opslab.common.formats.graphnode.GraphNodeText;
import ru.org.opslab.common.xml.PlainXmlWriter;

public class PlainXmlWriterTest {

    @Test
    public void testWriteXmlGraphNodeOutputStreamBoolean() throws Exception {
        PlainXmlWriter x = new PlainXmlWriter();
        GraphNode html = new GraphNode("html");
        new GraphNodeText("\n", false, html);
        GraphNode head = new GraphNode("head", html);
        new GraphNodeText("\n", false, html);
        GraphNode body = new GraphNode("body", html);
        new GraphNodeText("\n", false, body);
        new GraphNodeText("\n", false, html);
        GraphNode title = new GraphNode("title", head);
        new GraphNodeText("Html Page Title!", false, title);
        new GraphNodeText("Some body text.\nblah-blah-blah\n<a href=\"#\">link</a>", false, body);
        new GraphNodeText("\n", false, body);
        GraphNode link = new GraphNode("a", body);
        link.setAttr("href", "http://example.com/");
        new GraphNodeText("click me!", false, link);
        new GraphNodeText("\n", false, body);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n".getBytes());
        x.writeXml(html, out, false);
        System.out.write(out.toByteArray());

        String res = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n<html>\n<head><title>Html Page Title!</title></head>\n<body>\nSome body text.\nblah-blah-blah\n&lt;a href=\"#\"&gt;link&lt;/a&gt;\n<a href=\"http://example.com/\">click me!</a>\n</body>\n</html>";
        assertTrue(res.equals(out.toString()));
    }

}
