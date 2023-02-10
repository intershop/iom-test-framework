package com.intershop.oms.test.servicehandler.orderstateservice.v2_0;

import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.ServiceProvider;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;
import com.intershop.oms.test.servicehandler.orderstateservice.OMSOrderStateServiceHandler;

public class OMSOrderStateServiceHandlerProviderV2_0 implements ServiceProvider<OMSOrderStateServiceHandler> {
    @Override
    public OMSOrderStateServiceHandler createServiceHandler(OMSDbHandler dbhandler, ServiceConfiguration serviceConfiguration) {
        return new OMSOrderStateServiceHandlerV2_0(serviceConfiguration);
    }

    @Override
    public String getVersion()
    {
        return "V2_0";
    }
}
