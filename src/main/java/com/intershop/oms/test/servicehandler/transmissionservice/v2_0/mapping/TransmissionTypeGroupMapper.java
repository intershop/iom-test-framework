package com.intershop.oms.test.servicehandler.transmissionservice.v2_0.mapping;

import com.intershop.oms.test.businessobject.transmission.OMSTransmissionTypeGroup;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransmissionTypeGroupMapper
{
    TransmissionTypeGroupMapper INSTANCE = Mappers.getMapper(TransmissionTypeGroupMapper.class);

    @Mapping(target = "transmissionTypeGroupName", expression = "java(apiTransmissionTypeGroup)")
    OMSTransmissionTypeGroup fromApiTransmissionTypeGroup(String apiTransmissionTypeGroup);

    @InheritInverseConfiguration
    default String toApiTransmissionTypeGroup(OMSTransmissionTypeGroup omsTransmissionTypeGroup)
    {
        return omsTransmissionTypeGroup.getTransmissionTypeGroupName();
    }
}
