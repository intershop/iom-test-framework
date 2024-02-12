package com.intershop.oms.test.servicehandler.gdprservice.v2_10.mapping;

import com.intershop.oms.test.businessobject.gdpr.OMSCustomerOrder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.gdpr.v2_10.model.CustomerOrder;

@Mapper(uses = {CustomerOrderPositionMapper.class})
public interface CustomerOrderMapper
{
    CustomerOrderMapper INSTANCE = Mappers.getMapper(CustomerOrderMapper.class);

    OMSCustomerOrder fromApiCustomerOrder(CustomerOrder customerOrder);

    CustomerOrder toApiCustomerOrder(OMSCustomerOrder omsCustomerOrder);
}

