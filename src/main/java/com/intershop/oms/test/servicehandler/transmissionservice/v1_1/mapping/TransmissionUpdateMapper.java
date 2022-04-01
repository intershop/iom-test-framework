package com.intershop.oms.test.servicehandler.transmissionservice.v1_1.mapping;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.transmission.v1_1.model.TransmissionUpdate;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionUpdate;

@Mapper(uses = TransmissionUpdateTypeMapper.class)
public interface TransmissionUpdateMapper
{
    TransmissionUpdateMapper INSTANCE = Mappers.getMapper(TransmissionUpdateMapper.class);

    OMSTransmissionUpdate fromApiTransmissionUpdate(TransmissionUpdate transmissionUpdate);

    @InheritInverseConfiguration
    public abstract TransmissionUpdate toApiTransmissionUpdate(OMSTransmissionUpdate omsTransmissionUpdate);

    @AfterMapping
    public default void fromApiTransmissionUpdateList(final List<TransmissionUpdate> transmissionUpdates, @MappingTarget final List<OMSTransmissionUpdate> omsTransmissionUpdates)
    {
        transmissionUpdates.stream().forEach(transmissionUpdate -> omsTransmissionUpdates.add(fromApiTransmissionUpdate(transmissionUpdate)));
    }

    @AfterMapping
    public default void toApiTransmissionUpdateList(final List<OMSTransmissionUpdate> omsTransmissionUpdates, @MappingTarget final List<TransmissionUpdate> transmissionUpdates)
    {
        omsTransmissionUpdates.stream().forEach(omsTransmissionUpdate -> transmissionUpdates.add(toApiTransmissionUpdate(omsTransmissionUpdate)));
    }
}