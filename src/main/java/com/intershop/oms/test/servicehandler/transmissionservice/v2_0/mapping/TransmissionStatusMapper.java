package com.intershop.oms.test.servicehandler.transmissionservice.v2_0.mapping;

import com.intershop.oms.test.businessobject.transmission.OMSTransmissionStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransmissionStatusMapper
{
    TransmissionStatusMapper INSTANCE = Mappers.getMapper(TransmissionStatusMapper.class);

    @Mapping(target = "value", expression = "java(apiTransmissionStatus)")
    OMSTransmissionStatus fromApiTransmissionStatus(String apiTransmissionStatus);

    default String toApiTransmissionStatus(OMSTransmissionStatus omsTransmissionStatus)
    {
        return omsTransmissionStatus.getValue();
    }
}
