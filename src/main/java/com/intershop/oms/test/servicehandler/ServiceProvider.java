package com.intershop.oms.test.servicehandler;

import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;

import java.util.function.BiFunction;

/**
 * Common interface that has to be implemented for every ServiceHandler. It provides a way for {@link ServiceHandlerFactory}
 * to instantiate ServiceHandlers in a generic fashion.
 *
 * @param <T> the service type provided
 */
public interface ServiceProvider<T>
{
    T createServiceHandler(OMSDbHandler omsDbHandler, ServiceConfiguration serviceConfiguration);
    String getVersion();
}
