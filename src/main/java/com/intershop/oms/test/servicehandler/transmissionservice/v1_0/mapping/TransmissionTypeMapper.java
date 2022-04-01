package com.intershop.oms.test.servicehandler.transmissionservice.v1_0.mapping;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.transmission.v1.model.TransmissionType;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionType;

@Mapper
public interface TransmissionTypeMapper
{
    TransmissionTypeMapper INSTANCE = Mappers.getMapper(TransmissionTypeMapper.class);

    OMSTransmissionType fromApiTransmissionType(TransmissionType transmissionType);

    @InheritInverseConfiguration
    public abstract TransmissionType toApiTransmissionType(OMSTransmissionType omsTransmissionType);

    @AfterMapping
    public default void fromApiTransmissionTypeList(final List<TransmissionType> transmissionTypes, @MappingTarget final List<OMSTransmissionType> omsTransmissionTypes)
    {
        transmissionTypes.stream().forEach(transmissionType -> omsTransmissionTypes.add(fromApiTransmissionType(transmissionType)));
    }

    @AfterMapping
    public default void toApiTransmissionTypeList(final List<OMSTransmissionType> omsTransmissionTypes, @MappingTarget final List<TransmissionType> transmissionTypes)
    {
        omsTransmissionTypes.stream().forEach(omsTransmissionType -> transmissionTypes.add(toApiTransmissionType(omsTransmissionType)));
    }
}