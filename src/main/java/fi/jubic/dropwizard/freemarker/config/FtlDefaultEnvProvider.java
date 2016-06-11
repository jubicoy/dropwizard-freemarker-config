package fi.jubic.dropwizard.freemarker.config;

import java.util.Map;

/**
 * Default implementation from {@link FtlEnvProvider} using
 * {@link System#getenv()}.
 *
 * @author Vilppu Vuorinen, vilppu.vuorinen@jubic.fi
 * @since 0.1, 11.6.2016.
 */
public class FtlDefaultEnvProvider implements FtlEnvProvider {
    //
    // FtlEnvProvider impl
    // **************************************************************
    @Override
    public Map<String, String> getenv () {
        return System.getenv();
    }
}
