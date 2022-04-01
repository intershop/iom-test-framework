package com.intershop.oms.test.servicehandler.transmissionservice.v1_1.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.transmission.v1_1.model.Transmission;
import com.intershop.oms.test.businessobject.transmission.OMSTransmission;

@Mapper(uses={TransmissionTypeMapper.class, TransmissionSubtypeMapper.class, LinkMapper.class})
public interface TransmissionMapper
{
    TransmissionMapper INSTANCE = Mappers.getMapper(TransmissionMapper.class);

    OMSTransmission fromApiTransmission(Transmission transmission);

    @InheritInverseConfiguration
    public abstract Transmission toApiTransmission(OMSTransmission omsTransmission);
}