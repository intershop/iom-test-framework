package com.intershop.oms.test.configuration;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.ConfigSourceProvider;

public class YamlConfigSourceProvider extends io.smallrye.config.source.yaml.YamlConfigSourceProvider implements ConfigSourceProvider
{
    private int ordinal;

    public YamlConfigSourceProvider(int ordinal)
    {
        this.ordinal = ordinal;
    }

    @Override
    public Iterable<ConfigSource> getConfigSources(ClassLoader forClassLoader)
    {
        List<ConfigSource> src = new ArrayList<>();
        src.addAll(loadConfigSources("testframework-config.yaml", ordinal));
        src.addAll(loadConfigSources("testframework-config.yml", ordinal));
        return src;
    }
}
