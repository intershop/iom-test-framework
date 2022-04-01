package com.intershop.oms.test.servicehandler.omsdb.v1;

import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.ServiceProvider;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;

public class OMSDbHandlerProviderV1 implements ServiceProvider<OMSDbHandler>
{
    // NOTE: trying to implement singleton pattern, should be feasible
    private static final Object lock = new Object();
    private static volatile OMSDbHandlerV1 INSTANCE;

    @Override
    public OMSDbHandler createServiceHandler(OMSDbHandler dbhandler, ServiceConfiguration configuration)
    {
        // cheap check without synchronization
        if(INSTANCE == null)
        {
            synchronized(lock)
            {
                // recheck in synchronized block to avoid concurrency issues
                if(INSTANCE == null)
                {
                    INSTANCE = new OMSDbHandlerV1(configuration);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public String getVersion()
    {
        return "V1";
    }
}
