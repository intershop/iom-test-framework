package com.intershop.oms.test.servicehandler;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.grizzly.connector.GrizzlyConnectorProvider;

import com.intershop.oms.rest.shared.ApiClient;
import com.intershop.oms.rest.shared.logging.SLF4JClientLoggingHandler;
import com.intershop.oms.rest.shared.logging.SLF4JWriterInterceptor;
import com.intershop.oms.test.configuration.ConfigBuilder;

public class ExtendedApiClient extends ApiClient
{
    @Override
    public ClientConfig getDefaultClientConfig()
    {
        ClientConfig defaultClientConfig = super.getDefaultClientConfig();
        if (ConfigBuilder.getDefault().clientLogging())
        {
            defaultClientConfig.register(SLF4JClientLoggingHandler.class);
            defaultClientConfig.register(SLF4JWriterInterceptor.class);
        }
        defaultClientConfig.connectorProvider(new GrizzlyConnectorProvider());
        return defaultClientConfig;
    }

}
