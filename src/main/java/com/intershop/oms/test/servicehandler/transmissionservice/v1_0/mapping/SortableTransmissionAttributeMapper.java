package com.intershop.oms.test.servicehandler.transmissionservice.v1_0.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.transmission.v1.model.SortableTransmissionAttribute;
import com.intershop.oms.test.businessobject.transmission.OMSSortableTransmissionAttribute;

@Mapper
public interface SortableTransmissionAttributeMapper
{
    SortableTransmissionAttributeMapper INSTANCE = Mappers.getMapper(SortableTransmissionAttributeMapper.class);

    OMSSortableTransmissionAttribute fromApiSortableTransmissionAttribute(SortableTransmissionAttribute sortableTransmissionAttribute);

    @InheritInverseConfiguration
    public abstract SortableTransmissionAttribute toApiSortableTransmissionAttribute(OMSSortableTransmissionAttribute omsSortableTransmissionAttribute);
}
