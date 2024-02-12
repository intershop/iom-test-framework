package com.intershop.oms.test.servicehandler.gdprservice.v2_10;

import com.intershop.oms.rest.gdpr.v2_10.api.ShopApi;
import com.intershop.oms.rest.gdpr.v2_10.model.Customer;
import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.test.businessobject.gdpr.OMSCustomer;
import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.RESTServiceHandler;
import com.intershop.oms.test.servicehandler.gdprservice.OMSGDPRServiceHandler;
import com.intershop.oms.test.servicehandler.gdprservice.v2_10.mapping.CustomerMapper;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Set;

public class OMSGDPRServiceHandlerV2_10 extends RESTServiceHandler implements OMSGDPRServiceHandler
{
    private static final Logger log = LoggerFactory.getLogger(OMSGDPRServiceHandlerV2_10.class);
    private final OMSDbHandler dbHandler;
    private final ShopApi shopApi;

    public OMSGDPRServiceHandlerV2_10(OMSDbHandler dbHandler, ServiceConfiguration serviceConfig) {
        super(serviceConfig, "/rest/gdpr", log);
        this.dbHandler = dbHandler;
        this.shopApi = new ShopApi(apiClient);
    }

    @Override
    protected Collection<Object> unwrapApiClient() {
        return Set.of(shopApi);
    }

    @Override
    public OMSCustomer getCustomer(String customerShopNo, String shopName) throws ApiException {
        Customer customer = shopApi.getCustomer(customerShopNo, shopName);
        return CustomerMapper.INSTANCE.fromApiCustomer(customer);
    }

    @Override
    public void deleteCustomer(String customerShopNo, String shopName) throws ApiException {
        shopApi.deleteCustomer(customerShopNo, shopName);
    }
}
