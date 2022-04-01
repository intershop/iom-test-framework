package com.intershop.oms.test.configuration;

import io.smallrye.config.WithName;

import java.util.Optional;

public interface ServiceCredentials {
    @WithName("user")
    Optional<String> user();

    @WithName("password")
    Optional<String> password();

    @WithName("token")
    Optional<String> token();
}
