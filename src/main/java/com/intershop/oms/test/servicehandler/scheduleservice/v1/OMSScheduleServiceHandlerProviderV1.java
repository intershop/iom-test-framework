package com.intershop.oms.test.servicehandler.scheduleservice.v1;

import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.ServiceProvider;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;
import com.intershop.oms.test.servicehandler.scheduleservice.OMSScheduleServiceHandler;

public class OMSScheduleServiceHandlerProviderV1 implements ServiceProvider<OMSScheduleServiceHandler> {

    @Override
    public OMSScheduleServiceHandler createServiceHandler(OMSDbHandler dbhandler, ServiceConfiguration serviceConfiguration) {
        return new OMSScheduleServiceHandlerV1(serviceConfiguration);
    }

    @Override
    public String getVersion()
    {
        return "V1_0";
    }
}
