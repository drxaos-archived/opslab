package ru.org.opslab.common.junit;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import junit.framework.Assert;

import org.junit.Test;
import org.xml.sax.SAXException;

import ru.org.opslab.common.errors.IncorrectInput;
import ru.org.opslab.common.errors.ParameterMustNotBeNull;
import ru.org.opslab.common.formats.graphnode.GraphNode;
import ru.org.opslab.common.formats.graphnode.GraphNodeText;
import ru.org.opslab.common.formats.graphnode.InfoNode;
import ru.org.opslab.common.xml.DomXmlReader;
import ru.org.opslab.common.xml.IndentingXmlWriter;

public class GraphNodeTest {

    @Test
    public void testGraphNode() throws ParameterMustNotBeNull {
        new GraphNode();

        new GraphNode("1");
        try {
            new GraphNode(null);
            Assert.fail("must throw exception");
        } catch (ParameterMustNotBeNull e) {
            //ok
        }

        GraphNode node = new GraphNode();
        new GraphNode("2", node);
        try {
            new GraphNode("3", null);
            Assert.fail("must throw exception");
        } catch (ParameterMustNotBeNull e) {
            //ok
        }
        try {
            new GraphNode(null, node);
            Assert.fail("must throw exception");
        } catch (ParameterMustNotBeNull e) {
            //ok
        }
        try {
            new GraphNode(null, null);
            Assert.fail("must throw exception");
        } catch (ParameterMustNotBeNull e) {
            //ok
        }

    }

    @Test
    public void testAddChild() throws ParameterMustNotBeNull {
        GraphNode node = new GraphNode("1");
        node.addChild();

        /*
        node.addChild("edge1");
        try {
            node.addChild((String) null);
            Assert.fail("must throw exception");
        } catch (ParameterMustNotBeNull e) {
            //ok
        }
        */

        GraphNode node1 = new GraphNode("1");
        GraphNode node2 = new GraphNode("2");
        node1.addChild(node2);
        node1.addChild(node1);
        try {
            node2.addChild((GraphNode) null);
            Assert.fail("must throw exception");
        } catch (ParameterMustNotBeNull e) {
            //ok
        }

        node1.addChild(node2, "edge1");
        node1.addChild(node1, "edge2");
        try {
            node2.addChild(null, null);
            Assert.fail("must throw exception");
        } catch (ParameterMustNotBeNull e) {
            //ok
        }
        try {
            node2.addChild(null, "123");
            Assert.fail("must throw exception");
        } catch (ParameterMustNotBeNull e) {
            //ok
        }
        try {
            node2.addChild(node1, null);
            Assert.fail("must throw exception");
        } catch (ParameterMustNotBeNull e) {
            //ok
        }
    }

    @Test
    public void testAddParent() throws ParameterMustNotBeNull {
        GraphNode node = new GraphNode("1");
        node.addParent();

        node.addParent("edge1");
        try {
            node.addParent((String) null);
            Assert.fail("must throw exception");
        } catch (ParameterMustNotBeNull e) {
            //ok
        }

        GraphNode node1 = new GraphNode("1");
        GraphNode node2 = new GraphNode("2");
        node1.addParent(node2);
        node1.addParent(node1);
        try {
            node2.addParent((GraphNode) null);
            Assert.fail("must throw exception");
        } catch (ParameterMustNotBeNull e) {
            //ok
        }

        node1.addParent(node2, "edge1");
        node1.addParent(node1, "edge2");
        try {
            node2.addParent(null, null);
            Assert.fail("must throw exception");
        } catch (ParameterMustNotBeNull e) {
            //ok
        }
        try {
            node2.addParent(null, "123");
            Assert.fail("must throw exception");
        } catch (ParameterMustNotBeNull e) {
            //ok
        }
        try {
            node2.addParent(node1, null);
            Assert.fail("must throw exception");
        } catch (ParameterMustNotBeNull e) {
            //ok
        }
    }

    @Test
    public void testRemoveChildrenParentsEdges() throws ParameterMustNotBeNull {
        GraphNode node1 = new GraphNode("1");
        GraphNode node2 = new GraphNode("2");
        GraphNode node3 = new GraphNode("2");
        GraphNode node4 = new GraphNode("3");
        GraphNode node5 = new GraphNode("3");
        GraphNode node6 = new GraphNode("3");
        GraphNode node7 = new GraphNode("4");

        node1.addChild(node2, "12");
        node1.addChild(node4, "14");
        node1.addChild(node6, "16");
        node2.addChild(node3, "23");
        node2.addChild(node5, "25");
        node2.addChild(node6, "26");
        node3.addChild(node1, "31");
        node4.addChild(node7, "47");
        node5.addChild(node1, "51");
        node6.addChild(node7, "67");

        Assert.assertEquals(3, node1.getChildEdges().length);
        Assert.assertEquals(3, node2.getChildEdges().length);
        Assert.assertEquals(1, node3.getChildEdges().length);
        Assert.assertEquals(1, node4.getChildEdges().length);
        Assert.assertEquals(1, node5.getChildEdges().length);
        Assert.assertEquals(1, node6.getChildEdges().length);

        node1.removeChildren();
        Assert.assertEquals(0, node1.getChildEdges().length);

        node2.removeChildren(node5);
        Assert.assertEquals(2, node2.getChildEdges().length);

        node2.removeChildren("26");
        Assert.assertEquals(1, node2.getChildEdges().length);

        node2.removeChildren("27");
        Assert.assertEquals(1, node2.getChildEdges().length);

        node2.removeChildren(node3, "22");
        Assert.assertEquals(1, node2.getChildEdges().length);

        node2.removeChildren(node3, "23");
        Assert.assertEquals(0, node2.getChildEdges().length);

        node2.removeChildren(node3, "23");
        Assert.assertEquals(0, node2.getChildEdges().length);

        Assert.assertEquals(2, node7.getParentEdges().length);

        node7.removeParents("47");
        Assert.assertEquals(1, node7.getParentEdges().length);

        node7.removeParents(node6);
        Assert.assertEquals(0, node7.getParentEdges().length);

        node7.removeParents(node6);
        Assert.assertEquals(0, node7.getParentEdges().length);

        Assert.assertEquals(3, node1.findNodes(null, true).length);

    }

    @Test
    public void testFindNodes() throws ParameterMustNotBeNull, IncorrectInput {
        GraphNode node1 = new GraphNode("1");
        GraphNode node2 = new GraphNode("2");
        GraphNode node3 = new GraphNode("2");
        GraphNode node4 = new GraphNode("3");
        GraphNode node5 = new GraphNode("3");
        GraphNode node6 = new GraphNode("3");
        GraphNode node7 = new GraphNode("4");

        node1.addChild(node2);
        node1.addChild(node4);
        node1.addChild(node6);
        node2.addChild(node3);
        node2.addChild(node5);
        node2.addChild(node6);
        node3.addChild(node1);
        node4.addChild(node7);
        node5.addChild(node1);
        node6.addChild(node7);

        node1.setAttr("kk", "vv");
        node4.setAttr("k", "v");
        node7.setAttr("k", "v");

        Assert.assertEquals(1, node1.findNodes("1").length);
        Assert.assertEquals(2, node1.findNodes("2").length);
        Assert.assertEquals(3, node1.findNodes("3").length);
        Assert.assertEquals(1, node1.findNodes("4").length);
        Assert.assertEquals(7, node1.findNodes((String) null).length);
        Assert.assertEquals(7, node1.findNodes().length);
        Assert.assertEquals(0, node1.findNodes("0").length);

        Assert.assertEquals(1, node1.findNodes("1", false).length);
        Assert.assertEquals(2, node1.findNodes("2", false).length);
        Assert.assertEquals(3, node1.findNodes("3", false).length);
        Assert.assertEquals(1, node1.findNodes("4", false).length);
        Assert.assertEquals(7, node1.findNodes(null, false).length);
        Assert.assertEquals(0, node1.findNodes("0", false).length);

        Assert.assertEquals(1, node1.findNodes("1", true).length);
        Assert.assertEquals(2, node1.findNodes("2", true).length);
        Assert.assertEquals(1, node1.findNodes("3", true).length);
        Assert.assertEquals(0, node1.findNodes("4", true).length);
        Assert.assertEquals(4, node1.findNodes(null, true).length);
        Assert.assertEquals(0, node1.findNodes("0", true).length);

        Assert.assertEquals(2, node1.findNodes("k", "v").length);
        Assert.assertEquals(1, node1.findNodes("kk", "vv").length);
        Assert.assertEquals(0, node1.findNodes("kkk", "vvv").length);

        node1.removeChildren(node2);

        Assert.assertEquals(1, node1.findNodes("1").length);
        Assert.assertEquals(0, node1.findNodes("2").length);
        Assert.assertEquals(2, node1.findNodes("3").length);
        Assert.assertEquals(1, node1.findNodes("4").length);
        Assert.assertEquals(4, node1.findNodes((String) null).length);
        Assert.assertEquals(4, node1.findNodes().length);
        Assert.assertEquals(0, node1.findNodes("0").length);

        Assert.assertEquals(1, node2.findNodes("1").length);
        Assert.assertEquals(2, node2.findNodes("2").length);
        Assert.assertEquals(3, node2.findNodes("3").length);
        Assert.assertEquals(1, node2.findNodes("4").length);
        Assert.assertEquals(7, node2.findNodes((String) null).length);
        Assert.assertEquals(7, node2.findNodes().length);
        Assert.assertEquals(0, node2.findNodes("0").length);

        Assert.assertEquals(0, node7.findNodes("1").length);
        Assert.assertEquals(0, node7.findNodes("2").length);
        Assert.assertEquals(0, node7.findNodes("3").length);
        Assert.assertEquals(1, node7.findNodes("4").length);

        Assert.assertEquals(1, node7.findNodes("1", true).length);
        Assert.assertEquals(2, node7.findNodes("2", true).length);
        Assert.assertEquals(3, node7.findNodes("3", true).length);
        Assert.assertEquals(1, node7.findNodes("4", true).length);
        Assert.assertEquals(7, node7.findNodes(null, true).length);
        Assert.assertEquals(0, node7.findNodes("0", true).length);

        Assert.assertEquals(0, node2.findNodes("1", true).length);
        Assert.assertEquals(1, node2.findNodes("2", true).length);
        Assert.assertEquals(0, node2.findNodes("3", true).length);
        Assert.assertEquals(0, node2.findNodes("4", true).length);
    }

    @Test
    public void testGetSetCopyAttrAttrs() throws ParameterMustNotBeNull, IncorrectInput {
        GraphNode node1 = new GraphNode("1");
        GraphNode node2 = new GraphNode("2");
        GraphNode node3 = new GraphNode("3");
        GraphNode node4 = new GraphNode("4");

        node1.setAttr("attr1", "val1");
        node2.setAttr("attr2", "val2");
        node3.setAttr("attr3", "val3");
        node4.setAttr("attr4", "val4");

        Assert.assertEquals("val1", node1.getAttr("attr1", null));
        Assert.assertEquals("val2", node2.getAttr("attr2", null));
        Assert.assertEquals("val3", node3.getAttr("attr3", null));
        Assert.assertEquals(null, node4.getAttr("attr1", null));

        node2.setAttr("attr2", "val4");
        Assert.assertEquals("val4", node2.getAttr("attr2", null));

        node1.setAttr("attr1", null);
        node2.removeAttr("attr2");
        Assert.assertEquals(null, node1.getAttr("attr1", null));
        Assert.assertEquals(null, node2.getAttr("attr2", null));

        node3.setAttr("attr5", "val5");
        node3.setAttr("attr6", "val6");
        node3.setAttr("attr7", "val7");

        Assert.assertTrue(node3.hasAttr("attr5", "val5"));
        Assert.assertFalse(node3.hasAttr("attr6", "val5"));

        node1.copyAttr(node2);
        Assert.assertEquals(0, node1.getAttrs().length);
        node1.copyAttr(node3);
        Assert.assertEquals(4, node1.getAttrs().length);
        node1.copyAttr(node4);
        Assert.assertEquals(5, node1.getAttrs().length);

        node1.copyAttr(node1, "qwerty");
        Assert.assertEquals(5, node1.getAttrs().length);
        node1.removeAttr("attr5");
        Assert.assertEquals(4, node1.getAttrs().length);
        node1.copyAttr(node3, "attr5");
        Assert.assertEquals(5, node1.getAttrs().length);
    }

    @Test
    public void testGetSetName() throws ParameterMustNotBeNull {
        GraphNode node1 = new GraphNode();
        GraphNode node2 = new GraphNode("1");
        Assert.assertEquals("", node1.getName());
        Assert.assertEquals("1", node2.getName());
        node1.setName("A");
        node2.setName("A");
        Assert.assertEquals("A", node1.getName());
        Assert.assertEquals("A", node2.getName());
    }

    @Test
    public void testGetChildrenParentsNodes() throws ParameterMustNotBeNull {
        GraphNode node1 = new GraphNode("1");
        GraphNode node2 = new GraphNode("2");
        GraphNode node3 = new GraphNode("2");
        GraphNode node4 = new GraphNode("3");
        GraphNode node5 = new GraphNode("3");
        GraphNode node6 = new GraphNode("3");

        node1.addChild(node2);
        node1.addChild(node4);
        node1.addChild(node6);
        node3.addChild(node1);
        node5.addChild(node1);

        Assert.assertEquals(3, node1.getChildren().length);
        Assert.assertEquals(2, node1.getParents().length);
        Assert.assertEquals(2, node1.getChildren("3").length);
        Assert.assertEquals(1, node1.getParents("2").length);
        Assert.assertEquals(0, node1.getChildren("5").length);

    }

    @Test
    public void testGetChildParentEdges() throws ParameterMustNotBeNull {
        GraphNode node1 = new GraphNode("1");
        GraphNode node2 = new GraphNode("2");
        GraphNode node3 = new GraphNode("2");
        GraphNode node4 = new GraphNode("3");
        GraphNode node5 = new GraphNode("3");
        GraphNode node6 = new GraphNode("3");

        node1.addChild(node2, "a");
        node1.addChild(node4, "a");
        node1.addChild(node6, "b");
        node3.addChild(node1, "a");
        node5.addChild(node1, "b");
        node5.addChild(node1, "a");

        Assert.assertEquals(3, node1.getChildEdges().length);
        Assert.assertEquals(3, node1.getParentEdges().length);
        Assert.assertEquals(2, node1.getChildEdges("a").length);
        Assert.assertEquals(1, node1.getParentEdges("b").length);
        Assert.assertEquals(0, node1.getChildEdges("c").length);
        Assert.assertEquals(0, node1.getParentEdges(node1).length);
        Assert.assertEquals(1, node1.getParentEdges(node3).length);
        Assert.assertEquals(2, node1.getParentEdges(node5).length);
    }

    @Test
    public void testIsCommentText() throws ParameterMustNotBeNull {
        GraphNode node1 = new GraphNode("1");
        GraphNode node2 = new GraphNodeText("2", false);
        GraphNode node3 = new GraphNodeText("3", true);

        Assert.assertEquals(false, node1.isComment());
        Assert.assertEquals(false, node1.isText());
        Assert.assertEquals(true, node1.isTag());
        Assert.assertEquals(false, node2.isComment());
        Assert.assertEquals(true, node2.isText());
        Assert.assertEquals(false, node2.isTag());
        Assert.assertEquals(true, node3.isComment());
        Assert.assertEquals(false, node3.isText());
        Assert.assertEquals(false, node3.isTag());

    }

    @Test
    public void testToString() throws ParameterMustNotBeNull {
        GraphNode node1 = new GraphNode("1");
        GraphNode node2 = new GraphNode("2");
        GraphNode node3 = new GraphNode("2");
        GraphNode node4 = new GraphNode("3");
        GraphNode node5 = new GraphNode("3");
        GraphNode node6 = new GraphNode("3");
        GraphNode node7 = new GraphNode("4");

        node1.addChild(node2, "12");
        node1.addChild(node4, "14");
        node1.addChild(node6, "16");
        node2.addChild(node3, "23");
        node2.addChild(node5, "25");
        node2.addChild(node6, "26");
        node3.addChild(node1, "31");
        node4.addChild(node7, "47");
        node5.addChild(node1, "51");
        node6.addChild(node7, "67");
        node6.addChild(node6, "66");

        node1.toString();
        node2.toString();
        node3.toString();
        node4.toString();
        node5.toString();
        node6.toString();
        node7.toString();
    }

    private int counter;

    private void testInfo(InfoNode n, String[] infoNames, String[] infoIds) {
        String name = n.getName();
        String expname = infoNames[counter];
        String id = n.getId();
        String expid = infoIds[counter];
        //System.out.println(name + ": " + id);
        counter++;
        if (!n.isLink()) {
            for (InfoNode info : n.getInfoNodes()) {
                testInfo(info, infoNames, infoIds);
            }
        }
        Assert.assertEquals(expname, name);
        Assert.assertEquals(expid, id);
    }

    @Test
    public void testUpdateRemoveInfo() throws ParameterMustNotBeNull, IOException, XMLStreamException {
        GraphNode node1 = new GraphNode("1");
        GraphNode node2 = new GraphNode("2");
        GraphNode node3 = new GraphNode("3");
        GraphNode node4 = new GraphNode("4");
        GraphNode node5 = new GraphNode("5");
        GraphNode node6 = new GraphNode("6");
        GraphNode node7 = new GraphNode("7");

        node1.addChild(node2, "12");
        node1.addChild(node3, "13");
        node2.addChild(node4, "24");
        node2.addChild(node5, "25");
        node3.addChild(node4, "34");
        node3.addChild(node5, "35");
        node5.addChild(node6, "56");
        node6.addChild(node7, "67");
        node6.addChild(node5, "65");
        node7.addChild(node5, "75");

        String[] infoNames, infoIds;

        infoNames = new String[] { "1", "2", "4", "5", "6", "7", null, null, "3", null, null };
        infoIds = new String[] { null, null, "4_null_id1", "5_null_id2", null, null, "5_null_id2", "5_null_id2", null, "4_null_id1", "5_null_id2" };
        node1.updateInfo();
        counter = 0;
        testInfo(node1.getRootInfoNode(), infoNames, infoIds);

        node4.addChild(node7, "47");

        infoNames = new String[] { "1", "2", "4", "7", "5", "6", null, null, null, "3", null, null };
        infoIds = new String[] { null, null, "4_null_id1", "7_null_id2", "5_null_id3", null, "7_null_id2", "5_null_id3", "5_null_id3", null, "4_null_id1", "5_null_id3" };
        node1.updateInfo();
        counter = 0;
        testInfo(node1.getRootInfoNode(), infoNames, infoIds);

        node1.removeInfo();

    }

    @Test
    public void testReadWrite() throws ParameterMustNotBeNull, IOException, XMLStreamException, ParserConfigurationException, SAXException, IncorrectInput {
        GraphNode node1 = new DomXmlReader().readXml(new FileInputStream("src/ru/org/opslab/junit/1.xml"));
        new IndentingXmlWriter().writeXml(node1, new FileOutputStream("src/ru/org/opslab/junit/2.xml"));
    }

}
