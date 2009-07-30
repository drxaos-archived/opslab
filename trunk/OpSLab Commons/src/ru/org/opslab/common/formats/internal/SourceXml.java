package ru.org.opslab.common.formats.internal;

import ru.org.opslab.common.formats.graphnode.GraphNode;
import ru.org.opslab.common.utils.logging.Log;

public class SourceXml extends InternalFormat {

    public GraphNode project;

    public boolean isValid() {
        return project != null;
    }

    public void write(String fileName, String[] paramSequence) {
        if (fileName == null || fileName.length() == 0) {
            Log.getLogger().error("Source XML file name is not set");
        } else {
            makeXml(fileName, paramSequence, project);
        }
    }
}
