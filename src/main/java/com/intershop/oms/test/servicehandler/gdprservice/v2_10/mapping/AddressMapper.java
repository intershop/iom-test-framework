package com.intershop.oms.test.servicehandler.gdprservice.v2_10.mapping;

import com.intershop.oms.test.businessobject.gdpr.OMSAddress;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.gdpr.v2_10.model.Address;

@Mapper
public interface AddressMapper
{
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    OMSAddress fromApiAddress(Address address);

    Address toApiAddress(OMSAddress omsAddress);
}

