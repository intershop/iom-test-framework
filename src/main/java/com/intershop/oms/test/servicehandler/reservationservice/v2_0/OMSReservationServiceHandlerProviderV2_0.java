package com.intershop.oms.test.servicehandler.reservationservice.v2_0;

import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.ServiceProvider;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;
import com.intershop.oms.test.servicehandler.reservationservice.OMSReservationServiceHandler;

public class OMSReservationServiceHandlerProviderV2_0 implements ServiceProvider<OMSReservationServiceHandler>
{
    // FIXME doesn't support BearerAuth yet
    @Override
    public OMSReservationServiceHandler createServiceHandler(OMSDbHandler dbhandler, ServiceConfiguration configuration)
    {
        return new OMSReservationServiceHandlerV2_0(dbhandler, configuration);
    }

    @Override
    public String getVersion()
    {
        return "V2_0";
    }
}
