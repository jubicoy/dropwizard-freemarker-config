package fi.jubic.dropwizard.freemarker.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.configuration.ConfigurationFactory;
import io.dropwizard.configuration.ConfigurationFactoryFactory;

import javax.validation.Validator;

/**
 * @author Vilppu Vuorinen, vilppu.vuorinen@jubic.fi
 * @since 0.1, 11.6.2016.
 */
public class FtlConfigurationFactoryFactory<T> implements ConfigurationFactoryFactory<T> {
    //
    // Fields
    // **************************************************************
    private final FtlEnvProvider envProvider;

    //
    // Constructor(s)
    // **************************************************************
    public FtlConfigurationFactoryFactory (
            FtlEnvProvider envProvider
    ) {
        this.envProvider = envProvider;
    }

    //
    // ConfigurationFactoryFactory impl
    // **************************************************************
    @Override
    public ConfigurationFactory<T> create (
            Class<T> aClass,
            Validator validator,
            ObjectMapper objectMapper,
            String s
    ) {
        return new FtlConfigurationFactory<T>(
                aClass,
                validator,
                objectMapper,
                s,
                envProvider
        );
    }
}
