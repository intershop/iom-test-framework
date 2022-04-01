package com.intershop.oms.test.configuration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;

import java.nio.file.Path;
import java.nio.file.Paths;

import static io.smallrye.common.constraint.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ConfigBuilderTest
{

    @Test
    @ResourceLock(value = "system-properties", mode = ResourceAccessMode.READ)
    void fromClassPath()
    {
        Configuration cfg = ConfigBuilder.fromClassPath("testconfig.yaml");

        assertEquals("testvalue", cfg.databaseConfigs().get("default").getParameterValue("key"));
        assertEquals("blah", cfg.databaseConfigs().get("default").getParameterValue("anotherKey"));
        assertEquals("testframework-config.yaml", cfg.databaseConfigs().get("default").getParameterValue("keyOnlyInTFConfigYaml"));
        assertTrue(cfg.clientLogging());
    }

    @Test
    @ResourceLock(value = "system-properties", mode = ResourceAccessMode.READ)
    void fromWorkingDirectory()
    {
        Configuration cfg = ConfigBuilder.fromPath(Paths.get("src/test/resources/testconfig.yaml"));

        assertEquals("testvalue", cfg.databaseConfigs().get("default").getParameterValue("key"));
        assertEquals("V1_2", cfg.getOrderServiceById("default").getVersion());
        assertEquals("blah", cfg.databaseConfigs().get("default").getParameterValue("anotherKey"));
        assertEquals("fallback", cfg.getOrderServiceById("default").getParameterValue("testsubst"));
        assertNull(cfg.getOrderServiceById("alternative-without-params").getParameterValue("test"));
    }

    @Test
    @ResourceLock(value = "system-properties", mode = ResourceAccessMode.READ_WRITE)
    void fromAbsolutePath()
    {
        Path path = Paths.get("", "src/test/resources/testconfig.yaml");
        String testSysProp = "XYZ";
        System.setProperty("sys.prop.test", testSysProp);
        Configuration cfg = ConfigBuilder.fromPath(path.toAbsolutePath());

        assertEquals("testvalue", cfg.databaseConfigs().get("default").getParameterValue("key"));
        assertEquals("blah", cfg.databaseConfigs().get("default").getParameterValue("anotherKey"));
        assertEquals(testSysProp, cfg.getOrderServiceById("default").getParameterValue("testsubst"));
        System.clearProperty("sys.prop.test");
    }

}