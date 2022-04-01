package com.intershop.oms.test.servicehandler.rmaservice.v2_10;

import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.ServiceProvider;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;
import com.intershop.oms.test.servicehandler.rmaservice.OMSReturnRequestServiceHandler;

public class OMSReturnRequestServiceHandlerProviderV2_10 implements ServiceProvider<OMSReturnRequestServiceHandler>
{
    @Override
    public OMSReturnRequestServiceHandler createServiceHandler(OMSDbHandler dbhandler,
                    ServiceConfiguration serviceConfiguration)
    {
        return new OMSReturnRequestServiceHandlerV2_10(serviceConfiguration, dbhandler);
    }

    @Override
    public String getVersion()
    {
        return "V2_10";
    }
}
