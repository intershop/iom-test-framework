package com.intershop.oms.test.servicehandler.orderservice.v2_3;

import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.ServiceProvider;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;
import com.intershop.oms.test.servicehandler.orderservice.OMSOrderServiceHandler;

public class OMSOrderServiceHandlerProviderV2_3 implements ServiceProvider<OMSOrderServiceHandler>
{
    @Override
    public OMSOrderServiceHandler createServiceHandler(OMSDbHandler dbhandler, ServiceConfiguration configuration)
    {
        return new OMSOrderServiceHandlerV2_3(dbhandler, configuration);
    }

    @Override
    public String getVersion()
    {
        return "V2_3";
    }
}
