package com.intershop.oms.test.servicehandler.orderservice.v2_3;

import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.ServiceProvider;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;
import com.intershop.oms.test.servicehandler.orderservice.OMSOrderServiceHandler;

public class OMSOrderServiceHandlerProviderV2_2 implements ServiceProvider<OMSOrderServiceHandler> {

    // FIXME doesn't support BearerAuth yet
    @Override
    public OMSOrderServiceHandler createServiceHandler(OMSDbHandler dbhandler, ServiceConfiguration configuration) {
        return new OMSOrderServiceHandlerV2_2(dbhandler, configuration);
    }

    @Override
    public String getVersion()
    {
        return "V2_2";
    }
}
