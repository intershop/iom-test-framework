package com.intershop.oms.test.servicehandler.orderstateservice.v1;

import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.ServiceProvider;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;
import com.intershop.oms.test.servicehandler.orderservice.OMSOrderStateServiceHandler;

public class OMSOrderStateServiceHandlerProviderV1 implements ServiceProvider<OMSOrderStateServiceHandler>
{
    @Override
    public OMSOrderStateServiceHandler createServiceHandler(OMSDbHandler dbhandler,
                    ServiceConfiguration serviceConfiguration)
    {
        return new OMSOrderStateServiceHandlerV1(serviceConfiguration);
    }

    @Override
    public String getVersion()
    {
        return "V1";
    }
}
