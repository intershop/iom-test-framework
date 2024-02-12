package com.intershop.oms.test.servicehandler.gdprservice.v2_10.mapping;

import com.intershop.oms.test.businessobject.gdpr.OMSCustomerBalance;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.gdpr.v2_10.model.CustomerBalance;

@Mapper
public interface CustomerBalanceMapper
{
    CustomerBalanceMapper INSTANCE = Mappers.getMapper(CustomerBalanceMapper.class);

    OMSCustomerBalance fromApiCustomerBalance(CustomerBalance customerBalance);

    CustomerBalance toApiCustomerBalance(OMSCustomerBalance omsCustomerBalance);
}

