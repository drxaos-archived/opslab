package ru.org.opslab.common.dot;

import java.io.OutputStream;

import org.kohsuke.graphviz.Graph;

import ru.org.opslab.common.utils.logging.Log;

public class IndentingDotWriter implements DotWriter {

    public void serialize(OutputStream out, Graph graph) throws Exception {
        Log.getLogger().info("Writing DOT Graph to stream");
        graph.writeTo(out);
    }

}
