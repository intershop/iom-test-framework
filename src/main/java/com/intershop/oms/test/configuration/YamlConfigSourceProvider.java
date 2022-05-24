package com.intershop.oms.test.configuration;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.ConfigSourceProvider;

public class YamlConfigSourceProvider extends io.smallrye.config.source.yaml.YamlConfigSourceProvider
                implements ConfigSourceProvider
{
    private int ordinal;
    private String baseFileName;

    public YamlConfigSourceProvider(int ordinal, String baseFileName)
    {
        this.ordinal = ordinal;
        this.baseFileName = baseFileName;
    }

    @Override
    public Iterable<ConfigSource> getConfigSources(ClassLoader forClassLoader)
    {
        List<ConfigSource> src = new ArrayList<>();
        src.addAll(loadConfigSources(baseFileName + ".yaml", ordinal));
        src.addAll(loadConfigSources(baseFileName + ".yml", ordinal));
        return src;
    }
}
