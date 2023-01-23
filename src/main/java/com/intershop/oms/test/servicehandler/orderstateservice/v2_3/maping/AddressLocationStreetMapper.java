package com.intershop.oms.test.servicehandler.orderstateservice.v2_3.maping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.order.v2_3.model.AddressLocationStreet;
import com.intershop.oms.test.businessobject.address.OMSAddressLocationStreet;

@Mapper
public interface AddressLocationStreetMapper
{
    AddressLocationStreetMapper INSTANCE = Mappers.getMapper(AddressLocationStreetMapper.class);

    OMSAddressLocationStreet fromApiAddressLocationStreet(AddressLocationStreet addressLocationStreet);

    @InheritInverseConfiguration
    public abstract AddressLocationStreet toApiAddressLocationStreet(OMSAddressLocationStreet omsAddressLocationStreet);
}
