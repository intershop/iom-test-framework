package com.intershop.oms.test.servicehandler.gdprservice.v2_10;

import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.ServiceProvider;
import com.intershop.oms.test.servicehandler.gdprservice.OMSGDPRServiceHandler;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;

public class OMSGDPRServiceHandlerProviderV2_10  implements ServiceProvider<OMSGDPRServiceHandler>
{
    @Override
    public OMSGDPRServiceHandler createServiceHandler(OMSDbHandler dbhandler, ServiceConfiguration configuration)
    {
        return new OMSGDPRServiceHandlerV2_10(dbhandler, configuration);
    }

    @Override
    public String getVersion()
    {
        return "V2_10";
    }
}
