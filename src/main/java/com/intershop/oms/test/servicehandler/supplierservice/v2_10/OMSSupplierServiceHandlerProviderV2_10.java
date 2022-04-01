package com.intershop.oms.test.servicehandler.supplierservice.v2_10;

import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.ServiceProvider;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;
import com.intershop.oms.test.servicehandler.supplierservice.OMSSupplierServiceHandler;

public class OMSSupplierServiceHandlerProviderV2_10 implements ServiceProvider<OMSSupplierServiceHandler> {

    @Override
    public OMSSupplierServiceHandler createServiceHandler(OMSDbHandler dbhandler, ServiceConfiguration serviceConfiguration) {
        return new OMSSupplierServiceHandlerV2_10(serviceConfiguration, dbhandler);
    }

    @Override
    public String getVersion()
    {
        return "V2_10";
    }
}
