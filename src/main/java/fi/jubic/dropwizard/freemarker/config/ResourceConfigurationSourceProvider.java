package fi.jubic.dropwizard.freemarker.config;

import io.dropwizard.configuration.ConfigurationSourceProvider;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Vilppu Vuorinen, vilppu.vuorinen@jubic.fi
 * @since 0.1.1, 24.6.2016.
 */
public class ResourceConfigurationSourceProvider implements ConfigurationSourceProvider {
    //
    // Constants
    // *************************************************************
    private static final String DEFAULT_CONFIG_PATH = "configuration.yml";

    //
    // Fields
    // *************************************************************
    private final String path;

    //
    // Constructor(s)
    // **************************************************************
    public ResourceConfigurationSourceProvider () {
        this.path = DEFAULT_CONFIG_PATH;
    }

    public ResourceConfigurationSourceProvider (String path) {
        this.path = path;
    }

    //
    // ConfigurationSourceProvider impl
    // **************************************************************
    @Override
    public InputStream open (String s) throws IOException {
        return getClass().getClassLoader().getResourceAsStream(path);
    }
}
