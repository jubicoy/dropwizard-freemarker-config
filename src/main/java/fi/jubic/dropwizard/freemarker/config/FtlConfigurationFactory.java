package fi.jubic.dropwizard.freemarker.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.dropwizard.configuration.ConfigurationException;
import io.dropwizard.configuration.ConfigurationFactory;
import io.dropwizard.configuration.ConfigurationSourceProvider;

import javax.validation.Validator;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vilppu Vuorinen, vilppu.vuorinen@jubic.fi
 * @since 0.1, 11.6.2016.
 */
public class FtlConfigurationFactory<T> extends ConfigurationFactory<T> {
    //
    // Fields
    // **************************************************************
    private final FtlEnvProvider envProvider;

    //
    // Constructor(s)
    // **************************************************************
    public FtlConfigurationFactory (
            Class<T> klass,
            Validator validator,
            ObjectMapper objectMapper,
            String propertyPrefix,
            FtlEnvProvider envProvider
    ) {
        super(klass, validator, objectMapper, propertyPrefix);
        this.envProvider = envProvider;
    }

    //
    // ConfigurationFactory impl
    // **************************************************************
    @Override
    public T build () throws IOException, ConfigurationException {
        ConfigurationSourceProvider provider = new ResourceConfigurationSourceProvider();
        return build(provider, "");
    }

    @Override
    public T build (
            ConfigurationSourceProvider provider,
            String path
    ) throws IOException, ConfigurationException {
        if (path == null) throw new NullPointerException();
        try (InputStream is = provider.open(path)) {
            return super.build(
                    new FtlConfigurationSourceProvider(
                            processStream(is)
                    ),
                    path
            );
        } catch (TemplateException ignore) {
            // try falling back to default provider
            return super.build(provider, path);
        }
    }

    //
    // Private methods
    // **************************************************************
    private InputStream processStream (
            InputStream is
    ) throws IOException, TemplateException {
        Template t = new Template(
                "configuration",
                new InputStreamReader(is),
                new Configuration(Configuration.VERSION_2_3_23)
        );

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Map<String, Object> model = new HashMap<>();
        model.put("env", envProvider.getenv());
        t.process(model, new OutputStreamWriter(out));
        return new ByteArrayInputStream(out.toByteArray());
    }
}
