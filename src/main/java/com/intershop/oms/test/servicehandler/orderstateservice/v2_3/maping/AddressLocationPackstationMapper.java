package com.intershop.oms.test.servicehandler.orderstateservice.v2_3.maping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.order.v2_3.model.AddressLocationPackstation;
import com.intershop.oms.test.businessobject.address.OMSAddressLocationPackstation;

@Mapper
public interface AddressLocationPackstationMapper
{
    AddressLocationPackstationMapper INSTANCE = Mappers.getMapper(AddressLocationPackstationMapper.class);

    OMSAddressLocationPackstation fromApiAddressLocationPackstation(AddressLocationPackstation addressLocationPackstation);

    @InheritInverseConfiguration
    public abstract AddressLocationPackstation toApiAddressLocationPackstation(OMSAddressLocationPackstation omsAddressLocationPackstation);
}
