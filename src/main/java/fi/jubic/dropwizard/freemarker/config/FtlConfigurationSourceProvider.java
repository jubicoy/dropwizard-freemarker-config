package fi.jubic.dropwizard.freemarker.config;

import io.dropwizard.configuration.ConfigurationSourceProvider;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Vilppu Vuorinen, vilppu.vuorinen@jubic.fi
 * @since 0.1, 11.6.2016.
 */
public class FtlConfigurationSourceProvider implements ConfigurationSourceProvider {
    //
    // Fields
    // **************************************************************
    private final InputStream ftlStream;

    //
    // Constructor(s)
    // **************************************************************
    public FtlConfigurationSourceProvider (InputStream ftlStream) {
        this.ftlStream = ftlStream;
    }

    //
    // ConfigurationSourceProvider impl
    // **************************************************************
    @Override
    public InputStream open (String s) throws IOException {
        return ftlStream;
    }
}
