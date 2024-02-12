package com.intershop.oms.test.servicehandler.gdprservice;

import com.intershop.oms.test.businessobject.gdpr.OMSCustomer;
import com.intershop.oms.test.servicehandler.OMSServiceHandler;
import com.intershop.oms.rest.shared.ApiException;

public interface OMSGDPRServiceHandler extends OMSServiceHandler
{
    /**
     * Returns GDPR relevant customer data
     */
    OMSCustomer getCustomer(String customerShopNo, String shopName) throws ApiException;

    /**
     * Deletes GDPR relevant customer data
     */
    void deleteCustomer(String customerShopNo, String shopName) throws ApiException;
}
