package com.intershop.oms.test.servicehandler.transmissionservice.v2_0.mapping;

import com.intershop.oms.rest.transmission.v2_0.model.Transmission;
import com.intershop.oms.test.businessobject.transmission.OMSTransmission;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { TransmissionTypeMapper.class, TransmissionTypeGroupMapper.class, LinkMapper.class,
                TransmissionStatusMapper.class, TransmissionResponseStatusMapper.class, ReceiverTypeMapper.class})
public interface TransmissionSearchRequestMapper
{
    TransmissionSearchRequestMapper INSTANCE = Mappers.getMapper(TransmissionSearchRequestMapper.class);

    OMSTransmission fromApiTransmission(Transmission transmission);

    @InheritInverseConfiguration
    Transmission toApiTransmission(OMSTransmission omsTransmission);
}