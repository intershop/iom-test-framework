package com.intershop.oms.test.servicehandler.transmissionservice.v2_0.mapping;

import com.intershop.oms.rest.transmission.v2_0.model.TransmissionFilter;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionFilter;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { TransmissionTypeMapper.class, TransmissionTypeGroupMapper.class,
                TransmissionStatusMapper.class, TransmissionResponseStatusMapper.class, ReceiverTypeMapper.class})
public interface TransmissionFilterMapper
{
    TransmissionFilterMapper INSTANCE = Mappers.getMapper(TransmissionFilterMapper.class);

    OMSTransmissionFilter fromApiTransmissionFilter(TransmissionFilter apiTransmissionFilter);

    @InheritInverseConfiguration
    TransmissionFilter toApiTransmissionFilter(OMSTransmissionFilter omsTransmissionFilter);
}
