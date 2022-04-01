package com.intershop.oms.test.servicehandler.transmissionservice.v1_1.mapping;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.transmission.v1_1.model.TransmissionStatus;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionStatus;

@Mapper
public interface TransmissionStatusMapper
{
    TransmissionStatusMapper INSTANCE = Mappers.getMapper(TransmissionStatusMapper.class);

    OMSTransmissionStatus fromApiTransmissionStatus(TransmissionStatus TransmissionStatus);

    @InheritInverseConfiguration
    public abstract TransmissionStatus toApiTransmissionStatus(OMSTransmissionStatus omsTransmissionStatus);

    @AfterMapping
    public default void fromApiTransmissionStatusList(final List<TransmissionStatus> TransmissionStatuses, @MappingTarget final List<OMSTransmissionStatus> omsTransmissionStatuss)
    {
        TransmissionStatuses.stream().forEach(TransmissionStatus -> omsTransmissionStatuss.add(fromApiTransmissionStatus(TransmissionStatus)));
    }

    @AfterMapping
    public default void toApiTransmissionStatusList(final List<OMSTransmissionStatus> omsTransmissionStatuses, @MappingTarget final List<TransmissionStatus> transmissionStatuses)
    {
        omsTransmissionStatuses.stream().forEach(omsTransmissionStatus -> transmissionStatuses.add(toApiTransmissionStatus(omsTransmissionStatus)));
    }
}