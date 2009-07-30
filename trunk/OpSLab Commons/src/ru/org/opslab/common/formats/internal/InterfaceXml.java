package ru.org.opslab.common.formats.internal;

import org.kohsuke.graphviz.Graph;

import ru.org.opslab.common.formats.graphnode.GraphNode;
import ru.org.opslab.common.utils.logging.Log;

public class InterfaceXml extends InternalFormat {
    public GraphNode xml;
    public Graph projectStruct, classDiagram;

    public boolean isValid() {
        return (xml != null);
    }

    public void write(String xmlFileName, String projectStructFileName, String classDiagramFileName, String[] paramSequence) {
        if (xmlFileName == null || xmlFileName.length() == 0) {
            Log.getLogger().error("Interface XML file name is not set");
        } else {
            makeXml(xmlFileName, paramSequence, xml);
        }

        if (projectStructFileName == null || projectStructFileName.length() == 0) {
            Log.getLogger().error("Project Struct file name is not set");
        } else {
            makeDot(projectStructFileName, projectStruct);
        }

        if (classDiagramFileName == null || classDiagramFileName.length() == 0) {
            Log.getLogger().error("Class Diagram file name is not set");
        } else {
            makeDot(classDiagramFileName, classDiagram);
        }

    }
}
