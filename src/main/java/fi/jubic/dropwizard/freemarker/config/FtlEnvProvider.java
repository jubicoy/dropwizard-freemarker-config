package fi.jubic.dropwizard.freemarker.config;

import java.util.Map;

/**
 * @author Vilppu Vuorinen, vilppu.vuorinen@jubic.fi
 * @since 0.1, 11.6.2016.
 */
public interface FtlEnvProvider {
    /**
     * Provider method for current environment.
     * @return Environment variables
     */
    Map<String, String> getenv ();
}
