package com.intershop.oms.test.servicehandler.transmissionservice.v1_0.mapping;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.transmission.v1.model.TransmissionSubtype;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionSubtype;

@Mapper
public interface TransmissionSubtypeMapper
{
    TransmissionSubtypeMapper INSTANCE = Mappers.getMapper(TransmissionSubtypeMapper.class);

    OMSTransmissionSubtype fromApiTransmissionSubtype(TransmissionSubtype transmissionSubtype);

    @InheritInverseConfiguration
    public abstract TransmissionSubtype toApiTransmissionSubtype(OMSTransmissionSubtype omsTransmissionSubtype);

    @AfterMapping
    public default void fromApiTransmissionSubtypeList(final List<TransmissionSubtype> TransmissionSubtypes, @MappingTarget final List<OMSTransmissionSubtype> omsTransmissionSubtypes)
    {
        TransmissionSubtypes.stream().forEach(TransmissionSubtype -> omsTransmissionSubtypes.add(fromApiTransmissionSubtype(TransmissionSubtype)));
    }

    @AfterMapping
    public default void toApiTransmissionSubtypeList(final List<OMSTransmissionSubtype> omsTransmissionSubtypes, @MappingTarget final List<TransmissionSubtype> transmissionSubtypes)
    {
        omsTransmissionSubtypes.stream().forEach(omsTransmissionSubtype -> transmissionSubtypes.add(toApiTransmissionSubtype(omsTransmissionSubtype)));
    }
}