package com.intershop.oms.test.servicehandler.transmissionservice.v1_1.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.transmission.v1_1.model.TransmissionUpdateType;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionUpdateType;

@Mapper
public interface TransmissionUpdateTypeMapper
{
    TransmissionUpdateTypeMapper INSTANCE = Mappers.getMapper(TransmissionUpdateTypeMapper.class);

    OMSTransmissionUpdateType fromApiTransmissionUpdateType(TransmissionUpdateType TransmissionUpdateType);

    @InheritInverseConfiguration
    public abstract TransmissionUpdateType toApiTransmissionUpdateType(OMSTransmissionUpdateType omsTransmissionUpdateType);
}