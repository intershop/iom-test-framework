package com.intershop.oms.test.servicehandler.transmissionservice.v2_0.mapping;

import com.intershop.oms.test.businessobject.transmission.OMSSortableTransmissionAttribute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SortableTransmissionAttributeMapper
{
    SortableTransmissionAttributeMapper INSTANCE = Mappers.getMapper(SortableTransmissionAttributeMapper.class);

    @Mapping(target = "value", expression = "java(sortableTransmissionAttribute)")
    OMSSortableTransmissionAttribute fromApiSortableTransmissionAttribute(String sortableTransmissionAttribute);

    default String toApiSortableTransmissionAttribute(OMSSortableTransmissionAttribute omsSortableTransmissionAttribute)
    {
        return omsSortableTransmissionAttribute.getValue();
    }
}
