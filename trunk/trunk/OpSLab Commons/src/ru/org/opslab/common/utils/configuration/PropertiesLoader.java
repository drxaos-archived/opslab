package ru.org.opslab.common.utils.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class PropertiesLoader extends Loader {
    Configuration _loadedConfig;

    public PropertiesLoader(String filename) throws IOException {
        _loadedConfig = new PropertiesConfig(filename);
    }

    public PropertiesLoader(InputStream input) throws IOException {
        Reader reader = new InputStreamReader(input);
        _loadedConfig = new PropertiesConfig(reader);
    }

    @Override
    public Configuration getLoadedConfig() {
        return _loadedConfig;
    }

}
