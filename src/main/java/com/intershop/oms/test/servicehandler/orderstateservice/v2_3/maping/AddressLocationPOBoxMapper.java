package com.intershop.oms.test.servicehandler.orderstateservice.v2_3.maping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.order.v2_3.model.AddressLocationPOBox;
import com.intershop.oms.test.businessobject.address.OMSAddressLocationPOBox;

@Mapper
public interface AddressLocationPOBoxMapper
{
    AddressLocationPOBoxMapper INSTANCE = Mappers.getMapper(AddressLocationPOBoxMapper.class);

    OMSAddressLocationPOBox fromApiAddressLocationPOBox(AddressLocationPOBox addressLocationPOBox);

    @InheritInverseConfiguration
    public abstract AddressLocationPOBox toApiAddressLocationPOBox(OMSAddressLocationPOBox omsAddressLocationPOBox);
}
