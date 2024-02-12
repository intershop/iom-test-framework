package com.intershop.oms.test.servicehandler.gdprservice.v2_10.mapping;

import com.intershop.oms.test.businessobject.gdpr.OMSCustomerOrderPosition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.gdpr.v2_10.model.CustomerOrderPosition;

@Mapper(uses = {AddressMapper.class})
public interface CustomerOrderPositionMapper
{
    CustomerOrderPositionMapper INSTANCE = Mappers.getMapper(CustomerOrderPositionMapper.class);

    OMSCustomerOrderPosition fromApiCustomerOrderPosition(CustomerOrderPosition customerOrderPosition);

    CustomerOrderPosition toApiCustomerOrderPosition(OMSCustomerOrderPosition omsCustomerOrderPosition);
}

