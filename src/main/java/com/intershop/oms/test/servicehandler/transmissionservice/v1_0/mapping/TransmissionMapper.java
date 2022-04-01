package com.intershop.oms.test.servicehandler.transmissionservice.v1_0.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.transmission.v1.model.Transmission;
import com.intershop.oms.test.businessobject.transmission.OMSTransmission;

@Mapper(uses={TransmissionTypeMapper.class, TransmissionSubtypeMapper.class})
public interface TransmissionMapper
{
    TransmissionMapper INSTANCE = Mappers.getMapper(TransmissionMapper.class);

    @Mapping(target = "links", ignore = true)
    OMSTransmission fromApiTransmission(Transmission transmission);

    @InheritInverseConfiguration
    public abstract Transmission toApiTransmission(OMSTransmission omsTransmission);
}