package com.intershop.oms.test.configuration;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intershop.oms.test.util.Experimental;

import io.smallrye.config.SmallRyeConfig;
import io.smallrye.config.SmallRyeConfigBuilder;
import io.smallrye.config.source.yaml.YamlConfigSource;

@Experimental("Class is intended for internal use at this point")
public class ConfigBuilder
{
    private static final Logger log = LoggerFactory.getLogger(ConfigBuilder.class);
    private static final Configuration defaultConfig = initDefault();

    private static final int ORDINAL_DEFAULT_FILE = 150;
    private static final int ORDINAL_USERCONFIG_FILE = 155;

    public static Configuration fromClassPath(String path)
    {
        return load(Thread.currentThread().getContextClassLoader().getResource(path));
    }

    public static Configuration fromPath(Path path)
    {
        try
        {
            return load(path.toUri().toURL());
        }
        catch(MalformedURLException e)
        {
            throw new RuntimeException("error while loading config", e);
        }
    }

    private static Configuration load(URL url)
    {
        log.info("loading config from: " + url);
        SmallRyeConfigBuilder cb = new SmallRyeConfigBuilder();
        SmallRyeConfig cfg;
        try
        {
            cb = cb.withMapping(Configuration.class).addDefaultInterceptors().addDefaultSources()
                            .withSources(new YamlConfigSourceProvider(ORDINAL_USERCONFIG_FILE, "testframework-config.user"))
                            .addDiscoveredConverters().addDiscoveredValidator();
            if (url != null)
            {
                cb = cb.withSources(new YamlConfigSource(url, ORDINAL_DEFAULT_FILE));
            }
            else
            {
                cb = cb.withSources(new YamlConfigSourceProvider(ORDINAL_DEFAULT_FILE, "testframework-config"));
            }
            if (log.isDebugEnabled())
            {
                cb.getSourceProviders()
                                .forEach(sp -> log.debug("active config source provider: " + sp.getClass().getName()));
            }
            cfg = cb.build();

        }
        catch(IOException e)
        {
            throw new RuntimeException("error while loading config", e);
        }

        return cfg.getConfigMapping(Configuration.class);
    }

    public static Configuration getDefault()
    {
        return defaultConfig;
    }

    private static Configuration initDefault()
    {
        String configPath = System.getProperty("is.oms.testframework.configfile");
        return isNotBlank(configPath) ? fromPath(Paths.get(configPath)) : load(null);
    }

}
