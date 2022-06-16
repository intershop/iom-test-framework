package com.intershop.oms.test.servicehandler.transmissionservice.v2_0.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.transmission.v2_0.model.TransmissionUpdateType;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionUpdateType;

@Mapper
public interface TransmissionUpdateTypeMapper
{
    TransmissionUpdateTypeMapper INSTANCE = Mappers.getMapper(TransmissionUpdateTypeMapper.class);

    default OMSTransmissionUpdateType fromApiTransmissionUpdateType(TransmissionUpdateType transmissionUpdateType)
    {
        return new OMSTransmissionUpdateType(transmissionUpdateType.getValue());
    }

    default TransmissionUpdateType toApiTransmissionUpdateType(OMSTransmissionUpdateType omsTransmissionUpdateType)
    {
        return TransmissionUpdateType.fromValue(omsTransmissionUpdateType.getValue());
    }
}