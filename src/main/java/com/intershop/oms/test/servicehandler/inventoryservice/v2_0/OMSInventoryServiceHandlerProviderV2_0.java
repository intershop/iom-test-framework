package com.intershop.oms.test.servicehandler.inventoryservice.v2_0;

import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.ServiceProvider;
import com.intershop.oms.test.servicehandler.inventoryservice.OMSInventoryServiceHandler;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;

public class OMSInventoryServiceHandlerProviderV2_0 implements ServiceProvider<OMSInventoryServiceHandler>
{
    // FIXME doesn't support BearerAuth yet
    @Override
    public OMSInventoryServiceHandler createServiceHandler(OMSDbHandler dbhandler, ServiceConfiguration configuration)
    {
        return new OMSInventoryServiceHandlerV2_0(dbhandler, configuration);
    }

    @Override
    public String getVersion()
    {
        return "V2_0";
    }
}
