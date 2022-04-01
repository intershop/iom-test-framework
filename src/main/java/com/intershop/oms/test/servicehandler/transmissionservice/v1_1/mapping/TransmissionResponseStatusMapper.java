package com.intershop.oms.test.servicehandler.transmissionservice.v1_1.mapping;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.transmission.v1_1.model.TransmissionResponseStatus;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionResponseStatus;

@Mapper
public interface TransmissionResponseStatusMapper
{
    TransmissionResponseStatusMapper INSTANCE = Mappers.getMapper(TransmissionResponseStatusMapper.class);

    OMSTransmissionResponseStatus fromApiTransmissionResponseStatus(TransmissionResponseStatus TransmissionResponseStatus);

    @InheritInverseConfiguration
    public abstract TransmissionResponseStatus toApiTransmissionResponseStatus(OMSTransmissionResponseStatus omsTransmissionResponseStatus);

    @AfterMapping
    public default void fromApiTransmissionResponseStatusList(final List<TransmissionResponseStatus> TransmissionResponseStatuses, @MappingTarget final List<OMSTransmissionResponseStatus> omsTransmissionResponseStatuss)
    {
        TransmissionResponseStatuses.stream().forEach(TransmissionResponseStatus -> omsTransmissionResponseStatuss.add(fromApiTransmissionResponseStatus(TransmissionResponseStatus)));
    }

    @AfterMapping
    public default void toApiTransmissionResponseStatusList(final List<OMSTransmissionResponseStatus> omsTransmissionResponseStatuses, @MappingTarget final List<TransmissionResponseStatus> transmissionResponseStatuses)
    {
        omsTransmissionResponseStatuses.stream().forEach(omsTransmissionResponseStatus -> transmissionResponseStatuses.add(toApiTransmissionResponseStatus(omsTransmissionResponseStatus)));
    }
}