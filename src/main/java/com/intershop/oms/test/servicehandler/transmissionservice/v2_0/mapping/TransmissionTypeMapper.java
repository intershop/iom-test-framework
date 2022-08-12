package com.intershop.oms.test.servicehandler.transmissionservice.v2_0.mapping;

import com.intershop.oms.test.businessobject.transmission.OMSTransmissionType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransmissionTypeMapper
{
    TransmissionTypeMapper INSTANCE = Mappers.getMapper(TransmissionTypeMapper.class);

    @Mapping(target = "value", expression = "java(apiTransmissionType)")
    @Mapping(target = "allValues", ignore = true)
    OMSTransmissionType fromApiTransmissionType(String apiTransmissionType);

    default String toApiTransmissionType(OMSTransmissionType omsTransmissionType)
    {
        return omsTransmissionType.getValue();
    }
}
