package ru.org.opslab.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ru.org.opslab.common.errors.JustShowHelp;
import ru.org.opslab.common.utils.configuration.Configuration;
import ru.org.opslab.common.utils.configuration.PropertiesLoader;
import ru.org.opslab.common.utils.logging.Log;

/**
 * Класс, обеспечивающий загрузку конфигурации
 */
public class CommandLineParser {
    static public void parse(String[] args) throws Exception {
        if (args.length == 0 || args[0].equals("--help") || args[0].equals("-h") || args[0].equals("/?")) {
            throw new JustShowHelp("Help request in command line");
        } else if (args.length > 0 && (args[0].equals("--default") || args[0].equals("-d"))) {
            InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("osv.properties");
            String fname = "default.properties";
            if (args.length > 1) {
                fname = args[1];
            }
            OutputStream out = new FileOutputStream(fname);
            byte[] b = new byte[512];
            while (in.read(b) != -1) {
                out.write(b);
            }
            System.exit(0);
        } else {
            Log.configureLogging(Log.LOGGING_DEBUG);
            if (args.length > 0 && new File(args[0]).exists()) {
                try {
                    Configuration.loadConfig(new PropertiesLoader(args[0]));
                } catch (IOException e) {
                    Log.getLogger().error("Error while loading config [" + args[0] + "]", e);
                }
                Log.loadProperties(Configuration.getConfig().getProperties());
            }
        }
    }

    static public void showCommonHelp() {
        System.out.println("Common parameters:");
        System.out.println("    -h, --help    - Show help and exit.");
        System.out.println("    -d [<filename>], --default [<filename>] - Write config template.");
        System.out.println("    <filename> - Read config and run program.");
    }
}
