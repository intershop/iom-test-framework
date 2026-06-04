package com.intershop.oms.test.servicehandler;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.grizzly.connector.GrizzlyConnectorProvider;

import com.intershop.oms.rest.shared.ApiClient;
import com.intershop.oms.rest.shared.logging.SLF4JClientLoggingHandler;
import com.intershop.oms.rest.shared.logging.SLF4JWriterInterceptor;
import com.intershop.oms.test.configuration.ConfigBuilder;

public class ExtendedApiClient extends ApiClient
{
    // WildFly's Undertow HTTP listener default no-request-timeout is 60 s.
    // The ning/Grizzly AHC default pooledConnectionIdleTimeout is also 60 s,
    // creating a race: the server may close a connection just as the client
    // reuses it, producing "Remotely closed" (ProcessingException). Setting
    // the client-side idle timeout well below the server's value ensures the
    // client evicts stale connections before WildFly closes them.
    private static final int POOLED_CONNECTION_IDLE_TIMEOUT_MS = 40_000;

    @Override
    public ClientConfig getDefaultClientConfig()
    {
        ClientConfig defaultClientConfig = super.getDefaultClientConfig();
        if (ConfigBuilder.getDefault().clientLogging())
        {
            defaultClientConfig.register(SLF4JClientLoggingHandler.class);
            defaultClientConfig.register(SLF4JWriterInterceptor.class);
        }
        defaultClientConfig.connectorProvider(new GrizzlyConnectorProvider(
                (client, config, builder) -> builder.setPooledConnectionIdleTimeout(POOLED_CONNECTION_IDLE_TIMEOUT_MS)));
        return defaultClientConfig;
    }

}
