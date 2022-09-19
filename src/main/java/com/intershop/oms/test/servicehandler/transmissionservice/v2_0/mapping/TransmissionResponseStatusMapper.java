package com.intershop.oms.test.servicehandler.transmissionservice.v2_0.mapping;

import com.intershop.oms.test.businessobject.transmission.OMSTransmissionResponseStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransmissionResponseStatusMapper
{
    TransmissionResponseStatusMapper INSTANCE = Mappers.getMapper(TransmissionResponseStatusMapper.class);

    @Mapping(target = "value", expression = "java(transmissionResponseStatus)")
    OMSTransmissionResponseStatus fromApiTransmissionResponseStatus(String transmissionResponseStatus);

    default String toApiTransmissionResponseStatus(OMSTransmissionResponseStatus omsTransmissionResponseStatus)
    {
        return omsTransmissionResponseStatus.getValue();
    }
}