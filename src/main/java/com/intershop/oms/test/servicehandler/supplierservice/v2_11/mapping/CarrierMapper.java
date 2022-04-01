package com.intershop.oms.test.servicehandler.supplierservice.v2_11.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.communication.v2_11.model.Carrier;
import com.intershop.oms.test.businessobject.communication.OMSCarrier;

@Mapper
public interface CarrierMapper
{
    CarrierMapper INSTANCE = Mappers.getMapper(CarrierMapper.class);

    OMSCarrier fromApiCarrier(Carrier carrier);

    @InheritInverseConfiguration
    public abstract Carrier toApiCarrier(OMSCarrier omsCarrier);
}
