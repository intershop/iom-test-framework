package com.intershop.oms.test.configuration;

import io.smallrye.config.WithName;

import java.util.Optional;

public interface ServiceEndpoint {
    @WithName("protocol")
    Optional<String> protocol();

    @WithName("host")
    String host();

    @WithName("port")
    Optional<Integer> port();
}
