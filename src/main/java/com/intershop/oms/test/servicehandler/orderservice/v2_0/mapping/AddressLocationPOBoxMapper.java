package com.intershop.oms.test.servicehandler.orderservice.v2_0.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.order.v2_0.model.AddressLocationPOBox;
import com.intershop.oms.test.businessobject.address.OMSAddressLocationPOBox;

@Mapper
public interface AddressLocationPOBoxMapper
{
    AddressLocationPOBoxMapper INSTANCE = Mappers.getMapper(AddressLocationPOBoxMapper.class);

    OMSAddressLocationPOBox fromApiAddressLocationPOBox(AddressLocationPOBox addressLocationPOBox);

    @InheritInverseConfiguration
    public abstract AddressLocationPOBox toApiAddressLocationPOBox(OMSAddressLocationPOBox omsAddressLocationPOBox);
}
