package com.intershop.oms.test.servicehandler.transmissionservice.v1_1;

import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.ServiceProvider;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;
import com.intershop.oms.test.servicehandler.transmissionservice.OMSTransmissionServiceHandler;

public class OMSTransmissionServiceHandlerProviderV1_1 implements ServiceProvider<OMSTransmissionServiceHandler> {

    @Override
    public OMSTransmissionServiceHandler createServiceHandler(OMSDbHandler dbhandler, ServiceConfiguration serviceConfiguration) {
        return new OMSTransmissionServiceHandlerV1_1(serviceConfiguration);
    }

    @Override
    public String getVersion()
    {
        return "V1_1";
    }
}
