package com.intershop.oms.test.configuration;

import io.smallrye.config.WithName;

import java.util.Map;
import java.util.Optional;

public interface ServiceConfiguration {

    @WithName("version")
    String getVersion();

    @WithName("username")
    Optional<String> username();

    @WithName("password")
    Optional<String> password();

    @WithName("bearerAuthToken")
    Optional<String> bearerAuthToken();

    @WithName("endpoint")
    Optional<ServiceEndpoint> serviceEndpoint();

    @WithName("parameters")
    Map<String, String> parameters();

    default String getParameterValue(String key) {
//        return getParameters().isPresent() ? getParameters().get().get(key) : null;
        return parameters().get(key);
    }

}
