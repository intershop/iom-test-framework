package com.intershop.oms.rest.shared.logging;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.client.ClientResponseFilter;

@Priority(Priorities.USER + 1)
public class SLF4JClientLoggingHandler implements ClientResponseFilter, ClientRequestFilter
{

    private static final Logger log = LoggerFactory.getLogger(SLF4JClientLoggingHandler.class);

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException
    {
        log.info("\nResponse\nStateCode: {}\nHeaders:\n{}\nBody:\n{}", responseContext.getStatus(),
                        MaskedHeaders.of(responseContext.getHeaders()),
                        LoggingIOStreamHandler.readEntity(responseContext));

    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException
    {
        log.info("\nRequest\nURL: {}\nMethod: {}\nHeaders:\n{}", requestContext.getUri(), requestContext.getMethod(),
                        MaskedHeaders.of(requestContext.getHeaders()));
    }
}
