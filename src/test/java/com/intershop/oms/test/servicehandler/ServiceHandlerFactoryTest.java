package com.intershop.oms.test.servicehandler;

import com.intershop.oms.test.servicehandler.orderservice.OMSOrderServiceHandler;
import com.intershop.oms.test.servicehandler.orderservice.v2_2.OMSOrderServiceHandlerProviderV2_2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;

class ServiceHandlerFactoryTest
{
    @Test
    @ResourceLock(value = "system-properties", mode = ResourceAccessMode.READ_WRITE)
    void registerServiceHandlerDuplicate()
    {
        System.setProperty("is.oms.testframework.configfile", "src/test/resources/testconfig.yaml");
        // register a service provider that has been registered statically
        Assertions.assertThrows(IllegalArgumentException.class,
                        () -> ServiceHandlerFactory.registerServiceHandler(OMSOrderServiceHandler.class,
                                        new OMSOrderServiceHandlerProviderV2_2()));
        System.clearProperty("is.oms.testframework.configfile");
    }

    @Test
    @ResourceLock(value = "system-properties", mode = ResourceAccessMode.READ)
    void testDefaultServiceEndpoint()
    {
        OMSOrderServiceHandler orderServiceHandlerWithDefaultEndpoint = ServiceHandlerFactory.getOrderServiceHandler("alternative-without-params");
        assertTrue(orderServiceHandlerWithDefaultEndpoint.getApiClient().getBasePath().contains("DEFAULT_HOST"));

        OMSOrderServiceHandler defaultOrderServiceHandler = ServiceHandlerFactory.getOrderServiceHandler("default");
        assertTrue(defaultOrderServiceHandler.getApiClient().getBasePath().contains("CONFIGURED_HOST"));
    }
}