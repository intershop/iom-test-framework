package com.intershop.oms.test.servicehandler.rmaservice.v2_12.mapping;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.rma.v2_12.model.ReadReturnRequestPosition;
import com.intershop.oms.test.businessobject.rma.OMSReadReturnRequestPosition;

@Mapper(uses= {CustomAttributeMapper.class} )
public interface ReadReturnRequestPositionMapper
{
    ReadReturnRequestPositionMapper INSTANCE = Mappers.getMapper(ReadReturnRequestPositionMapper.class);

    @Mapping(target="customAttributesAsMap", ignore = true)
    OMSReadReturnRequestPosition fromApiReadReturnRequestPosition(ReadReturnRequestPosition readReturnRequestPosition);

    @InheritInverseConfiguration
    ReadReturnRequestPosition toApiReadReturnRequestPosition(OMSReadReturnRequestPosition omsReadReturnRequestPosition);

    @AfterMapping
    default void fromApiReadReturnRequestPositionList(final List<ReadReturnRequestPosition> readReturnRequestPositions, @MappingTarget final List<OMSReadReturnRequestPosition> omsReadReturnRequestPositions)
    {
        readReturnRequestPositions.stream().forEach(readReturnRequestPosition -> omsReadReturnRequestPositions.add(fromApiReadReturnRequestPosition(readReturnRequestPosition)));
    }

}