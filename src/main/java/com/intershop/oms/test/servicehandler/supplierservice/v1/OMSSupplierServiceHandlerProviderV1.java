package com.intershop.oms.test.servicehandler.supplierservice.v1;

import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.ServiceProvider;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;
import com.intershop.oms.test.servicehandler.supplierservice.OMSSupplierServiceHandler;

public class OMSSupplierServiceHandlerProviderV1 implements ServiceProvider<OMSSupplierServiceHandler> {

    @Override
    public OMSSupplierServiceHandler createServiceHandler(OMSDbHandler dbhandler, ServiceConfiguration serviceConfiguration) {
        return new OMSSupplierServiceHandlerV1(serviceConfiguration, dbhandler);
    }

    @Override
    public String getVersion()
    {
        return "V1";
    }
}
